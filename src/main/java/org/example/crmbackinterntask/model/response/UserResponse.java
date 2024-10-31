package org.example.crmbackinterntask.model.response;

import lombok.Data;

@Data
public class UserResponse {

    private String username;

    private String password;

    private String email;

    private String role;

    private String status;
}
