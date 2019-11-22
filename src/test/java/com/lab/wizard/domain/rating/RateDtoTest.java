package com.lab.wizard.domain.rating;

import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RateDtoTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        RateDto rateDto = new RateDto();
        //when
        //then
        assertNotNull(rateDto);
        assertNull(rateDto.getId());
        assertNull(rateDto.getName());
        assertNull(rateDto.getComment());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        RateDto rateDto = new RateDto(1L, "name", 3L, "comment");

        //when
        //then
        assertEquals(1L, rateDto.getId().longValue());
        assertEquals("name", rateDto.getName());
        assertEquals("comment", rateDto.getComment());
    }
}