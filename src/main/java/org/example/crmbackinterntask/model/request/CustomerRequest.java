package org.example.crmbackinterntask.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequest {

    private String communicationPersonName;

    private String companyName;

    @NotBlank(message = "phone can not be empty or null")
    @Pattern(regexp = "^[0-9]+$")
    @Size(min = 10, max = 12)
    private String phone;

}
