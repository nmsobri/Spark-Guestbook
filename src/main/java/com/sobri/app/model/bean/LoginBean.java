package com.sobri.app.model.bean;

import javax.validation.constraints.*;

public class LoginBean {

    @NotBlank(message = "Please enter email")
    public String email;

    @NotBlank(message = "Please enter password")
    public String password;

    public LoginBean(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
