package com.lab.wizard.service;

import com.lab.wizard.domain.user.Employee;
import com.lab.wizard.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(final Long id) {
        return repository.findById(id);
    }

    public Optional<Employee> getEmployeeByLicence(final String licence) {
        return repository.findByLicence(licence);
    }

    public Employee saveEmployee(final Employee employee) {
        return repository.save(employee);
    }

    public void deleteEmployee(final Long id) {
        repository.deleteById(id);
    }
}
