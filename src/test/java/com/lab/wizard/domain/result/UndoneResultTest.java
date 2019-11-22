package com.lab.wizard.domain.result;

import com.lab.wizard.domain.user.Patient;
import org.junit.Test;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UndoneResultTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        UndoneResult undoneResult = new UndoneResult();
        //when
        //then
        assertNotNull(undoneResult);
        assertNull(undoneResult.getId());
        assertNull(undoneResult.getPatient());
        assertNull(undoneResult.getMaterial());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        Patient patient = new Patient(1L, "firstname", "lastname", "pesel", "login", "password", null);
        UndoneResult undoneResult = new UndoneResult(1L, patient, "material", LocalDate.of(2019, 9, 10), false);

        //when
        //then
        assertEquals(1L, undoneResult.getId().longValue());
        assertEquals("material", undoneResult.getMaterial());
        assertFalse(undoneResult.isDone());
    }
}