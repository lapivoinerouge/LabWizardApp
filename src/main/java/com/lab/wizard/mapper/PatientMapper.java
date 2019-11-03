package com.lab.wizard.mapper;

import com.lab.wizard.domain.user.Patient;
import com.lab.wizard.domain.user.PatientDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    public Patient mapToPatient(final PatientDto dto) {
        return new Patient(
                dto.getFirstname(),
                dto.getLastname(),
                dto.getPesel(),
                dto.getEmail(),
                dto.getPassword());
    }

    public PatientDto mapToPatientDto(final Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getFirstname(),
                patient.getLastname(),
                patient.getPesel(),
                patient.getEmail(),
                patient.getPassword());
    }

    public List<PatientDto> mapToPatientDtoList(final List<Patient> patients) {
        return patients.stream()
                .map(p -> new PatientDto(p.getId(), p.getFirstname(), p.getLastname(), p.getPesel(), p.getEmail(), p.getPassword()))
                .collect(Collectors.toList());
    }
}
