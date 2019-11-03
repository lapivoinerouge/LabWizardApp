package com.lab.wizard.service;

import com.lab.wizard.domain.user.Patient;
import com.lab.wizard.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    public Optional<Patient> getPatientById(final Long id) {
        return repository.findById(id);
    }

    //find patient by pesel
    public Optional<Patient> getPatientByPesel(final String pesel) {
        return repository.findByPesel(pesel);
    }

    public Patient savePatient(final Patient patient) {
        return repository.save(patient);
    }

    public void deletePatient(final Long id) {
        repository.deleteById(id);
    }
}
