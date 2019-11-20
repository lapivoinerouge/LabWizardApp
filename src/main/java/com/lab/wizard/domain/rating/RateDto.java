package com.lab.wizard.domain.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {
    private Long id;
    private String name;
    private Long rate;
    private String comment;
}
