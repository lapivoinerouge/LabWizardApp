package com.lab.wizard.repository;

import com.lab.wizard.domain.user.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Override
    List<Patient> findAll();

    @Override
    Optional<Patient> findById(Long id);

    Optional<Patient> findByPesel(String pesel);

    @Override
    Patient save(Patient patient);

    @Override
    void deleteById(Long id);
}
