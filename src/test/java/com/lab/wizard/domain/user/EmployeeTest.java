package com.lab.wizard.domain.user;

import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EmployeeTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        Employee employee = new Employee();
        //when
        //then
        assertNotNull(employee);
        assertNull(employee.getId());
        assertNull(employee.getFirstname());
        assertNull(employee.getLastname());
        assertNull(employee.getLicence());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        Employee employee = new Employee(1L, "firstname", "lastname", "licence", "login", "password", "user");

        //when
        //then
        assertEquals(1L, employee.getId().longValue());
        assertEquals("firstname", employee.getFirstname());
        assertEquals("login", employee.getLogin());
    }
}