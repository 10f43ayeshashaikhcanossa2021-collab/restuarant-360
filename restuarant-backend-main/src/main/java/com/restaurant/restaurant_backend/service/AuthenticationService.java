package com.restaurant.restaurant_backend.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.restaurant.restaurant_backend.dto.GoogleLoginRequest;
import com.restaurant.restaurant_backend.dto.GoogleLoginResponse;
import com.restaurant.restaurant_backend.dto.LoginRequest;
import com.restaurant.restaurant_backend.dto.LogoutRequest;
import com.restaurant.restaurant_backend.dto.PinLoginRequest;
import com.restaurant.restaurant_backend.dto.RefreshRequest;
import com.restaurant.restaurant_backend.dto.RefreshResponse;
import com.restaurant.restaurant_backend.dto.RegisterRequest;
import com.restaurant.restaurant_backend.dto.UserResponse;
import com.restaurant.restaurant_backend.entity.RefreshToken;
import com.restaurant.restaurant_backend.entity.RoleEntity;
import com.restaurant.restaurant_backend.entity.Terminal;
import com.restaurant.restaurant_backend.entity.User;
import com.restaurant.restaurant_backend.oauth.GoogleTokenVerifier;
import com.restaurant.restaurant_backend.repository.OutletRepository;
import com.restaurant.restaurant_backend.repository.RoleRepository;
import com.restaurant.restaurant_backend.repository.TerminalRepository;
import com.restaurant.restaurant_backend.repository.UserRepository;



@Service
public class AuthenticationService {
        private final GoogleTokenVerifier googleTokenVerifier;
private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final OutletRepository outletRepository;
private final TerminalRepository terminalRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService customUserDetailsService;

    private final RefreshTokenService refreshTokenService;

    public AuthenticationService(

            AuthenticationManager authenticationManager,

            UserRepository userRepository,

            RoleRepository roleRepository,
            OutletRepository outletRepository,

            JwtService jwtService,

            PasswordEncoder passwordEncoder,

            CustomUserDetailsService customUserDetailsService,
            GoogleTokenVerifier googleTokenVerifier,

            RefreshTokenService refreshTokenService,
             TerminalRepository terminalRepository

    ){


        this.authenticationManager = authenticationManager;
        this.outletRepository = outletRepository;
        this.terminalRepository = terminalRepository;


        this.userRepository = userRepository;

        this.roleRepository = roleRepository;

        this.jwtService = jwtService;
         this.googleTokenVerifier = googleTokenVerifier;

        this.passwordEncoder = passwordEncoder;

        this.customUserDetailsService = customUserDetailsService;

        this.refreshTokenService = refreshTokenService;

    }







    // ===============================
    // LOGIN WITH EMAIL + PASSWORD
    // ===============================


    public String authenticate(LoginRequest request){



        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );



        User user =

                userRepository.findByEmail(request.getEmail())

                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );



        UserDetails userDetails =

                customUserDetailsService
                .loadUserByUsername(user.getEmail());



        return jwtService.generateToken(userDetails);


    }









    // ===============================
    // LOGIN + REFRESH TOKEN
    // ===============================


    public RefreshResponse authenticateWithRefresh(
            LoginRequest request
    ){


        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );



        User user =

                userRepository.findByEmail(request.getEmail())

                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );




        UserDetails userDetails =

                customUserDetailsService
                .loadUserByUsername(user.getEmail());



        String accessToken =

                jwtService.generateToken(userDetails);




        String refreshToken =

                refreshTokenService
                .createRefreshToken(user)
                .getToken();




        return new RefreshResponse(

                accessToken,

                refreshToken

        );


    }
    public RefreshResponse loginWithPin(PinLoginRequest request) {

    User user = userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    if (user.getPin() == null || user.getPin().isBlank()) {
        throw new RuntimeException("PIN not set");
    }
    if (user.getOutlet() == null) {
    throw new RuntimeException("No outlet assigned");
}

if (!user.getOutlet().getName().equals(request.getOutlet())) {
    throw new RuntimeException("Invalid Outlet");
}

    boolean validPin =
            passwordEncoder.matches(
                    request.getPin(),
                    user.getPin()
            );

    if (!validPin) {
        throw new RuntimeException("Invalid PIN");
    }
    Terminal terminal = terminalRepository
        .findByName(request.getTerminal())
        .orElseThrow(() ->
                new RuntimeException("Terminal not found"));

if (user.getTerminal() == null) {
    throw new RuntimeException("No terminal assigned");
}

if (!user.getTerminal().getId().equals(terminal.getId())) {
    throw new RuntimeException("Invalid terminal");
}

    UserDetails userDetails =
            customUserDetailsService
                    .loadUserByUsername(user.getEmail());

    String accessToken =
            jwtService.generateToken(userDetails);

    String refreshToken =
            refreshTokenService
                    .createRefreshToken(user)
                    .getToken();

    return new RefreshResponse(
            accessToken,
            refreshToken
    );
}







    // ===============================
    // REGISTER USER
    // ===============================


    public String register(RegisterRequest request){


if (userRepository.findByEmail(request.getEmail()).isPresent()) {
    throw new RuntimeException("User already registered. Please login.");
}





        RoleEntity role =

                roleRepository
                .findByName(request.getRole().name())

                .orElseThrow(

                () -> new RuntimeException(
                        "Role not found"
                )

        );







        User user = new User();



        user.setFullName(
                request.getFullName()
        );



        user.setEmail(
                request.getEmail()
        );



        user.setPassword(

                passwordEncoder.encode(
                        request.getPassword()
                )

        );
        if (request.getPin() != null && !request.getPin().isBlank()) {
    user.setPin(passwordEncoder.encode(request.getPin()));
}
       



        user.setRole(role);




        userRepository.save(user);






        UserDetails userDetails =

                customUserDetailsService

                .loadUserByUsername(
                        user.getEmail()
                );




        return jwtService.generateToken(userDetails);


    }









    // ===============================
    // REFRESH TOKEN
    // ===============================



    public RefreshResponse refreshToken(
            RefreshRequest request
    ){



        RefreshToken refreshToken =

                refreshTokenService
                .findByToken(
                        request.getRefreshToken()
                );




        if (Boolean.TRUE.equals(refreshToken.getRevoked())) {
    throw new RuntimeException("Refresh token revoked");
}




        if(refreshTokenService
                .isExpired(refreshToken)){


            throw new RuntimeException(
                    "Refresh token expired"
            );


        }





        User user =

                refreshToken.getUser();






        UserDetails userDetails =

                customUserDetailsService

                .loadUserByUsername(
                        user.getEmail()
                );






        String newAccessToken =

                jwtService.generateToken(
                        userDetails
                );






        return new RefreshResponse(

                newAccessToken,

                refreshToken.getToken()

        );


    }









    // ===============================
    // LOGOUT
    // ===============================



    public void logout(
            LogoutRequest request
    ){


        refreshTokenService
        .revokeRefreshToken(
                request.getRefreshToken()
        );


    }









    // ===============================
    // CURRENT USER
    // ===============================



    public UserResponse getCurrentUser(
            String email
    ){



        User user =

                userRepository
                .findByEmail(email)

                .orElseThrow(
                () -> new RuntimeException(
                        "User not found"
                )

        );






        RoleEntity role =

                user.getRole();







        UserResponse response =

                new UserResponse();





        response.setFullName(
                user.getFullName()
        );



        response.setEmail(
                user.getEmail()
        );



        response.setRole(role.getName());





        List<String> permissions =

                role.getPermissions()

                .stream()

                .map(permission -> permission.getName().name())

                .toList();





        response.setPermissions(
                permissions
        );





        return response;


    }









    // ===============================
    // CHANGE PASSWORD
    // ===============================



    public void changePassword(

            String email,

            String oldPassword,

            String newPassword

    ){



        User user =

                userRepository
                .findByEmail(email)

                .orElseThrow(
                () -> new RuntimeException(
                        "User not found"
                )

        );







        if(!passwordEncoder.matches(

                oldPassword,

                user.getPassword()

        )){


            throw new RuntimeException(
                    "Old password incorrect"
            );


        }






        user.setPassword(

                passwordEncoder.encode(
                        newPassword
                )

        );





        userRepository.save(user);



    }

   public GoogleLoginResponse googleLogin(GoogleLoginRequest request) throws Exception {

    Payload payload = googleTokenVerifier.verify(request.getToken());

    if (payload == null) {
        throw new RuntimeException("Invalid Google Token");
    }

    String email = payload.getEmail();
    String fullName = (String) payload.get("name");
    String googleId = payload.getSubject();

    User user = userRepository.findByEmail(email).orElse(null);

    if (user == null) {

        user = new User();

        user.setEmail(email);
        user.setFullName(fullName);

        user.setPassword(
                passwordEncoder.encode(
                        java.util.UUID.randomUUID().toString()
                )
        );

        user.setProvider("GOOGLE");
        user.setProviderId(googleId);
        user.setActive(true);

        // Default role
        RoleEntity role = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() ->
                        new RuntimeException("CUSTOMER role not found"));

        user.setRole(role);

        user = userRepository.save(user);
    }

    UserDetails userDetails =
            customUserDetailsService.loadUserByUsername(user.getEmail());

    String accessToken =
            jwtService.generateToken(userDetails);

    RefreshToken refreshToken =
            refreshTokenService.createRefreshToken(user);

    GoogleLoginResponse response = new GoogleLoginResponse();

    response.setAccessToken(accessToken);
    response.setRefreshToken(refreshToken.getToken());
    response.setFullName(user.getFullName());
    response.setEmail(user.getEmail());
    response.setRole(user.getRole().getName());

    return response;
}



}