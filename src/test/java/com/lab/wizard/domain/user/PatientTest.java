package com.lab.wizard.domain.user;

import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PatientTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        Patient patient = new Patient();
        //when
        //then
        assertNotNull(patient);
        assertNull(patient.getId());
        assertNull(patient.getFirstname());
        assertNull(patient.getLastname());
        assertNull(patient.getPassword());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        Patient patient = new Patient(1L, "firstname", "lastname", "pesel", "login", "password", null);

        //when
        //then
        assertEquals(1L, patient.getId().longValue());
        assertEquals("firstname", patient.getFirstname());
        assertEquals("pesel", patient.getPesel());
    }

}