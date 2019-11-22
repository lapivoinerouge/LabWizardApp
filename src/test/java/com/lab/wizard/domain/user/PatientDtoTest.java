package com.lab.wizard.domain.user;

import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PatientDtoTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        PatientDto patientDto = new PatientDto();
        //when
        //then
        assertNotNull(patientDto);
        assertNull(patientDto.getId());
        assertNull(patientDto.getFirstname());
        assertNull(patientDto.getLastname());
        assertNull(patientDto.getPassword());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        PatientDto patientDto = new PatientDto(1L, "firstname", "lastname", "pesel", "login", "password");

        //when
        //then
        assertEquals(1L, patientDto.getId().longValue());
        assertEquals("firstname", patientDto.getFirstname());
        assertEquals("pesel", patientDto.getPesel());
    }
}