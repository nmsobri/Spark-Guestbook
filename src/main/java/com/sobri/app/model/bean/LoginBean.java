package com.sobri.app.model.bean;

import javax.validation.constraints.*;

public class LoginBean {

    @NotBlank(message = "Please enter email")
    private String email;

    @NotBlank(message = "Please enter password")
    private String password;

    public LoginBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}