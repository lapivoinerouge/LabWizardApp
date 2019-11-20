package com.lab.wizard.mapper;

import com.lab.wizard.domain.rating.Rate;
import com.lab.wizard.domain.rating.RateDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RateMapper {

    public Rate mapToRate(final RateDto dto) {
        return new Rate(
                dto.getId(),
                dto.getName(),
                dto.getRate(),
                dto.getComment());
    }

    public RateDto mapToRateDto(final Rate rate) {
        return new RateDto(
                rate.getId(),
                rate.getName(),
                rate.getRate(),
                rate.getComment());
    }

    public List<RateDto> mapToRateDtoList(final List<Rate> rates) {
        return rates.stream()
                .map(r -> new RateDto(r.getId(), r.getName(), r.getRate(), r.getComment()))
                .collect(Collectors.toList());
    }
}
