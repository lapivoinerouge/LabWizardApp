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
    private String patientPesel;
    private String material;
    private LocalDate receiveDate;
}
