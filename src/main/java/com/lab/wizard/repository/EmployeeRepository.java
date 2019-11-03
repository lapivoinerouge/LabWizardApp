package com.lab.wizard.repository;

import com.lab.wizard.domain.user.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll();

    @Override
    Optional<Employee> findById(Long id);

    Optional<Employee> findByLicence(String licence);

    @Override
    Employee save(Employee employee);

    @Override
    void deleteById(Long id);
}
