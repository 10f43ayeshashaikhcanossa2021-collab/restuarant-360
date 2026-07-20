package com.restaurant.restaurant_backend.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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





    // Role table relation
    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleEntity role;
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

    public RoleEntity getRole() {
        return role;
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

    public void setRole(RoleEntity role) {
        this.role=role;
    }


    public void setProvider(String provider) {
        this.provider=provider;
    }


    public void setProviderId(String providerId) {
        this.providerId=providerId;
    }

}