package org.example.crmbackinterntask.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

    private String username;

    @Size(min = 8)
    private String password;

    @Email
    @NotBlank(message = "email cannot be empty or null")
    @Size(max = 40)
    private String email;

}
