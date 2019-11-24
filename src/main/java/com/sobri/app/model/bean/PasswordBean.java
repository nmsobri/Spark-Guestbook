package com.sobri.app.model.bean;

import javax.validation.constraints.NotBlank;

public class PasswordBean {
    @NotBlank(message = "Please enter password")
    private String password;

    @NotBlank(message = "Please enter confirm password")
    private String confirmPassword;

    public PasswordBean(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
