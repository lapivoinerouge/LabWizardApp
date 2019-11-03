package com.lab.wizard.mapper;

import com.lab.wizard.domain.user.Employee;
import com.lab.wizard.domain.user.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public Employee mapToEmployee(final EmployeeDto dto) {
        return new Employee(
                dto.getId(),
                dto.getFirstname(),
                dto.getLastname(),
                dto.getLicence(),
                dto.getLogin(),
                dto.getPassword(),
                dto.getRole());
    }

    public EmployeeDto mapToEmployeeDto(final Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getLicence(),
                employee.getLogin(),
                employee.getPassword(),
                employee.getRole());
    }

    public List<EmployeeDto> mapToEmployeeDtoList(final List<Employee> employees) {
        return employees.stream()
                .map(e -> new EmployeeDto(e.getId(), e.getFirstname(), e.getLastname(), e.getLicence(), e.getLogin(), e.getPassword(), e.getRole()))
                .collect(Collectors.toList());
    }
}
