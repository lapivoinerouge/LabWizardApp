package com.lab.wizard.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String pesel;
    private String email;
    private String password;
}
