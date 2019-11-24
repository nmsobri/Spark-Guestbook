package com.sobri.app.model.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ResetBean {

    @NotBlank(message = "Please enter email")
    @Email(message = "Please enter valid email")
    private String email;

    public ResetBean(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
