package com.restaurant.restaurant_backend.entity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String fullName;
@ManyToOne
@JoinColumn(name="terminal_id")
private Terminal terminal;

    @Column(nullable = false, unique = true)
    private String email;


    private String password;
    @Column(length = 255)
private String pin;
private boolean active=true;
private int failedLoginAttempts = 0;

private LocalDateTime accountLockedUntil;

public boolean isActive() {
    return active;
}

public void setActive(boolean active) {
    this.active = active;
}

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<RoleEntity> roles = new HashSet<>();

public Set<RoleEntity> getRoles() {
    return roles;
}

public void setRoles(Set<RoleEntity> roles) {
    this.roles = roles;
}

public void addRole(RoleEntity role) {
    this.roles.add(role);
}

public void removeRole(RoleEntity role) {
    this.roles.remove(role);
}
    // Role table relation
    
    @ManyToOne
@JoinColumn(name="outlet_id")
private Outlet outlet;



    @Column(nullable = false, columnDefinition = "varchar(255) default 'LOCAL'")
    private String provider = "LOCAL";


    private String providerId;



    public User() {
    }



    // =====================
    // GETTERS
    // =====================


    public Long getId() {
        return id;
    }


    public String getFullName() {
        return fullName;
    }
public Terminal getTerminal() {
    return terminal;
}

    public String getEmail() {
        return email;
    }

public Outlet getOutlet() {
    return outlet;
}
    public String getPassword() {
        return password;
    }
    public String getPin() {
    return pin;
}
public int getFailedLoginAttempts() {
    return failedLoginAttempts;
}

public LocalDateTime getAccountLockedUntil() {
    return accountLockedUntil;
}

    

    public String getProvider() {
        return provider;
    }


    public String getProviderId() {
        return providerId;
    }



    // =====================
    // SETTERS
    // =====================


    public void setId(Long id) {
        this.id=id;
    }


    public void setFullName(String fullName) {
        this.fullName=fullName;
    }

public void setTerminal(Terminal terminal) {
    this.terminal = terminal;
}
    public void setEmail(String email) {
        this.email=email;
    }
public void setOutlet(Outlet outlet) {
    this.outlet = outlet;
}

    public void setPassword(String password) {
        this.password=password;
    }
    public void setPin(String pin) {
    this.pin = pin;
}

public void setFailedLoginAttempts(int failedLoginAttempts) {
    this.failedLoginAttempts = failedLoginAttempts;
}

public void setAccountLockedUntil(LocalDateTime accountLockedUntil) {
    this.accountLockedUntil = accountLockedUntil;
}

   


    public void setProvider(String provider) {
        this.provider=provider;
    }


    public void setProviderId(String providerId) {
        this.providerId=providerId;
    }
    

}