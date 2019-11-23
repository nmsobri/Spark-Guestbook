package com.sobri.app.model.bean;

import javax.validation.constraints.*;

public class RegisterBean {
    @NotBlank(message = "Please enter email")
    @Email(message = "Please enter valid email")
    public String email;

    @NotBlank(message = "Please enter password")
    public String password;

    @NotBlank(message = "Please enter confirm password")
    public String confirmPassword;

    @NotBlank(message = "Please enter phone number")
    public String phoneNumber;

    public RegisterBean(String email, String password, String confirmPassword, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
    }
}
