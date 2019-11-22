package com.lab.wizard.domain.result;

import com.lab.wizard.domain.user.Employee;
import com.lab.wizard.domain.user.Patient;
import org.junit.Test;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResultTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        Result result = new Result();
        //when
        //then
        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getUndoneResult());
        assertNull(result.getComment());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        Patient patient = new Patient(1L, "firstname", "lastname", "pesel", "login", "password", null);
        UndoneResult undoneResult = new UndoneResult(1L, patient, "material", LocalDate.of(2019, 9, 10), false);
        Employee employee = new Employee(1L, "firstname", "lastname", "licence", "login", "password", "user");

        Result result = new Result(1L, undoneResult, "result", "comment", employee, LocalDate.now());

        //when
        //then
        assertEquals(1L, result.getId().longValue());
        assertEquals("material", result.getUndoneResult().getMaterial());
        assertEquals(LocalDate.now(), result.getFinishDate());
    }
}