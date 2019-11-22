package com.lab.wizard.domain.user;

import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EmployeeDtoTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        EmployeeDto employeeDto = new EmployeeDto();
        //when
        //then
        assertNotNull(employeeDto);
        assertNull(employeeDto.getId());
        assertNull(employeeDto.getFirstname());
        assertNull(employeeDto.getLastname());
        assertNull(employeeDto.getLicence());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        EmployeeDto employeeDto = new EmployeeDto(1L, "firstname", "lastname", "licence", "login", "password", "user");

        //when
        //then
        assertEquals(1L, employeeDto.getId().longValue());
        assertEquals("firstname", employeeDto.getFirstname());
        assertEquals("login", employeeDto.getLogin());
    }
}