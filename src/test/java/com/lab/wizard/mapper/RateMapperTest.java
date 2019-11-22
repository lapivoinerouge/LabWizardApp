package com.lab.wizard.mapper;

import com.lab.wizard.domain.rating.Rate;
import com.lab.wizard.domain.rating.RateDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RateMapperTest {

    private RateMapper mapper = new RateMapper();

    @Test
    public void testMapToTask() {
        //given
        RateDto rateDto = new RateDto(1L, "name", 5L, "comment");

        //when
        Rate rate = mapper.mapToRate(rateDto);

        //then
        assertEquals("name", rate.getName());
        assertEquals(5L, rate.getRate().longValue());
    }

    @Test
    public void testMapToTaskDto() {
        //given
        Rate rate = new Rate(1L, "name", 4L, "comment");

        //when
        RateDto rateDto = mapper.mapToRateDto(rate);

        //then
        assertEquals("name", rateDto.getName());
        assertEquals(4L, rateDto.getRate().longValue());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        List<Rate> rates = new ArrayList<>();
        rates.add(new Rate(1L, "name", 4L, "comment"));
        rates.add(new Rate(2L, "name2", 3L, "comment2"));

        //when
        List<RateDto> rateDtos = mapper.mapToRateDtoList(rates);

        //then
        assertEquals("name", rateDtos.get(0).getName());
        assertEquals(1, rateDtos.get(0).getId().longValue());
        assertEquals("comment2", rateDtos.get(1).getComment());
    }
}