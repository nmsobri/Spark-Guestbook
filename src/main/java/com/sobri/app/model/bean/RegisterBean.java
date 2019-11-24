package com.sobri.app.model.bean;

import com.sobri.app.model.validation.FieldMatch;
import com.sobri.app.model.validation.Number;

import javax.validation.constraints.*;

@FieldMatch(first = "password", second = "confirmPassword", message = "Confirm Password must match password")
public class RegisterBean {


    @NotBlank(message = "Please enter email")
    @Email(message = "Please enter valid email")
    private String email;

    @NotBlank(message = "Please enter password")
    private String password;

    @NotBlank(message = "Please enter confirm password")
    private String confirmPassword;

    @NotBlank(message = "Please enter phone number")
    @Number(message = "Please enter valid phone number")
    private String phoneNumber;

    public RegisterBean(String email, String password, String confirmPassword, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
