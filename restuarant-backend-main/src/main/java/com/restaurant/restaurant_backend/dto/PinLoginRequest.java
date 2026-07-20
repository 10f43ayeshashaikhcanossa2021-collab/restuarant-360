package com.restaurant.restaurant_backend.dto;

public class PinLoginRequest {

    private String email;
    private String pin;
    private String outlet;
    private String terminal;

    public PinLoginRequest() {
    }

    public String getEmail() {
        return email;
    }
    public String getTerminal() {
    return terminal;
}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getOutlet() {
    return outlet;
}

public void setOutlet(String outlet) {
    this.outlet = outlet;
}
public void setTerminal(String terminal) {
    this.terminal = terminal;
}
}