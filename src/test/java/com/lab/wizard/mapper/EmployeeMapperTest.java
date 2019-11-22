package com.lab.wizard.mapper;

import com.lab.wizard.domain.user.Employee;
import com.lab.wizard.domain.user.EmployeeDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeeMapperTest {

    private EmployeeMapper mapper = new EmployeeMapper();

    @Test
    public void testMapToTask() {
        //given
        EmployeeDto employeeDto = new EmployeeDto(1L, "firstname", "lastname", "licence", "login", "password", "user");

        //when
        Employee employee = mapper.mapToEmployee(employeeDto);

        //then
        assertEquals("password", employee.getPassword());
        assertEquals("user", employee.getRole());
    }

    @Test
    public void testMapToTaskDto() {
        //given
        Employee employee = new Employee(1L, "firstname", "lastname", "licence", "login", "password", "user");

        //when
        EmployeeDto employeeDto = mapper.mapToEmployeeDto(employee);

        //then
        assertEquals("user", employeeDto.getRole());
        assertEquals("password", employeeDto.getPassword());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "firstname", "lastname", "licence", "login", "password", "user"));
        employees.add(new Employee(1L, "firstname1", "lastname1", "licence1", "login1", "password1", "user1"));

        //when
        List<EmployeeDto> employeeDtos = mapper.mapToEmployeeDtoList(employees);

        //then
        assertEquals("login", employeeDtos.get(0).getLogin());
    }
}