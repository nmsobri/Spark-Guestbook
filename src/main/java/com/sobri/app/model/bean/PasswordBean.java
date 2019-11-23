package com.sobri.app.model.bean;

import javax.validation.constraints.NotBlank;

public class PasswordBean {
    @NotBlank(message = "Please enter password")
    public String password;

    @NotBlank(message = "Please enter confirm password")
    public String confirmPassword;

    public PasswordBean(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
