package com.lab.wizard.domain.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UndoneResultDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String pesel;
    private String material;
    private LocalDate receiveDate;
    private boolean done;
}
