package com.lab.wizard.domain.result;

import org.junit.Test;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResultDtoTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        ResultDto resultDto = new ResultDto();
        //when
        //then
        assertNotNull(resultDto);
        assertNull(resultDto.getId());
        assertNull(resultDto.getMaterial());
        assertNull(resultDto.getComment());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        ResultDto resultDto = new ResultDto(1L, 1L, "firstname", "lastname", "pesel", "material", LocalDate.of(2019, 9, 11), "result", "comment", "licence", LocalDate.now());

        //when
        //then
        assertEquals(1L, resultDto.getId().longValue());
        assertEquals("material", resultDto.getMaterial());
        assertEquals("comment", resultDto.getComment());
    }
}