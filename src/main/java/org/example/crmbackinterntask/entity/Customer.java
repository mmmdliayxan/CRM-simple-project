package org.example.crmbackinterntask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String communicationPersonName;

    private String companyName;

    private String phone;

    @Column(nullable = false,columnDefinition = "INT DEFAULT '1'")
    private Integer status=1;

}
