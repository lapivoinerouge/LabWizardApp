package com.lab.wizard.domain.result;

import org.junit.Test;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UndoneResultDtoTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        UndoneResultDto undoneResultDto = new UndoneResultDto();
        //when
        //then
        assertNotNull(undoneResultDto);
        assertNull(undoneResultDto.getId());
        assertNull(undoneResultDto.getFirstname());
        assertNull(undoneResultDto.getLastname());
        assertNull(undoneResultDto.getMaterial());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        UndoneResultDto undoneResultDto = new UndoneResultDto(1L, "firstname", "lastname", "pesel", "material", LocalDate.of(2019, 9, 10), false);

        //when
        //then
        assertEquals(1L, undoneResultDto.getId().longValue());
        assertEquals("firstname", undoneResultDto.getFirstname());
        assertEquals("material", undoneResultDto.getMaterial());
    }
}