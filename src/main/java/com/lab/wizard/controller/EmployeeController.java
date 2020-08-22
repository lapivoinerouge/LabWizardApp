package com.lab.wizard.controller;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.user.EmployeeDto;
import com.lab.wizard.mapper.EmployeeMapper;
import com.lab.wizard.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lab")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping(value = "/employees")
    public List<EmployeeDto> getEmployees() {
        return employeeMapper.mapToEmployeeDtoList(employeeService.getAllEmployees());
    }

    @GetMapping(value = "/employees/{id}")
    public EmployeeDto getEmployee(@PathVariable Long id) throws NotFoundException {
        return employeeMapper.mapToEmployeeDto(employeeService.getEmployeeById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @PostMapping(value = "/employees")
    public void addEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.saveEmployee(employeeMapper.mapToEmployee(employeeDto));
    }

    @PutMapping(value = "/employees")
    public void editEmployee(@RequestBody EmployeeDto employeeDto) throws NotFoundException {
        if (employeeService.getEmployeeById(employeeDto.getId()).isPresent()) {
            employeeMapper.mapToEmployeeDto(employeeService.saveEmployee(employeeMapper.mapToEmployee(employeeDto)));
        } else {
            throw new NotFoundException(employeeDto.getId());
        }
    }

    @DeleteMapping(value = "/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) throws NotFoundException {
        try {
            employeeService.deleteEmployee(id);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }
}
