package com.sobri.app.model.bean;

import javax.validation.constraints.*;

public class RegisterBean {
    @NotBlank(message = "Please enter email")
    private String email;

    @NotBlank(message = "Please enter password")
    private String password;

    @NotBlank(message = "Please enter confirm password")
    private String confirmPassword;

    @NotBlank(message = "Please enter phone number")
    private String phoneNumber;

    public RegisterBean(String email, String password, String confirmPassword, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
    }
}
