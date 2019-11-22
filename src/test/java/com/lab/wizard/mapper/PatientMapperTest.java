package com.lab.wizard.mapper;

import com.lab.wizard.domain.user.Patient;
import com.lab.wizard.domain.user.PatientDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PatientMapperTest {

    private PatientMapper mapper = new PatientMapper();

    @Test
    public void testMapToTask() {
        //given
        PatientDto patientDto = new PatientDto(1L, "firstname", "lastname", "pesel", "email", "password");

        //when
        Patient patient = mapper.mapToPatient(patientDto);

        //then
        assertEquals("email", patient.getEmail());
        assertEquals("password", patient.getPassword());
    }

    @Test
    public void testMapToTaskDto() {
        //given
        Patient patient = new Patient(1L, "firstname", "lastname", "pesel", "email", "password", null);

        //when
        PatientDto patientDto = mapper.mapToPatientDto(patient);

        //then
        assertEquals("email", patientDto.getEmail());
        assertEquals("password", patientDto.getPassword());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1L, "firstname", "lastname", "pesel", "email", "password", null));
        patients.add(new Patient(2L, "firstname1", "lastname2", "pesel2", "email2", "password2", null));

        //when
        List<PatientDto> patientDtos = mapper.mapToPatientDtoList(patients);

        //then
        assertEquals("lastname", patientDtos.get(0).getLastname());
    }
}