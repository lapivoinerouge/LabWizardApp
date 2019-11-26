package com.lab.wizard.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarEvent {
    @JsonProperty("name")
    private String name;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    private LocalDateTime endDate;
}
