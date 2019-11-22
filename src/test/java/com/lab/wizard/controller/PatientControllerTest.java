package com.lab.wizard.controller;

import com.google.gson.Gson;
import com.lab.wizard.domain.user.Patient;
import com.lab.wizard.domain.user.PatientDto;
import com.lab.wizard.mapper.PatientMapper;
import com.lab.wizard.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatientService service;
    @MockBean
    private PatientMapper mapper;

    @Test
    public void shouldGetAllPatients() throws Exception {
        //given
        List<PatientDto> list = new ArrayList<>();
        list.add(new PatientDto(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1"));
        list.add(new PatientDto(2L, "Firstname2", "Lastname2", "Pesel2", "Email2", "Password2"));

        when(mapper.mapToPatientDtoList(service.getAllPatients())).thenReturn(list);

        //when & then
        mockMvc.perform(get("/lab/patients").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstname", is("Firstname1")))
                .andExpect(jsonPath("$[1].password", is("Password2")));
    }

    @Test
    public void shouldGetPatientById() throws Exception {
        //given
        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
        PatientDto patientDto = new PatientDto(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1");
        when(service.getPatientById(1L)).thenReturn(Optional.of(patient));
        when(mapper.mapToPatientDto(patient)).thenReturn(patientDto);

        //when & then
        mockMvc.perform(get("/lab/patients/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is("Firstname1")))
                .andExpect(jsonPath("$.lastname", is("Lastname1")));
    }

    @Test
    public void shouldDeletePatient() throws Exception {
        //given
        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
        PatientDto patientDto = new PatientDto(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1");

        when(service.getPatientById(1L)).thenReturn(ofNullable(patient));

        //when & then
        mockMvc.perform(delete("/lab/patients/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldEditPatient() throws Exception {
        //given
        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
        PatientDto patientDto = new PatientDto(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1");

        when(mapper.mapToPatient(patientDto)).thenReturn(patient);
        when(service.savePatient(patient)).thenReturn(patient);
        when(mapper.mapToPatientDto(patient)).thenReturn(patientDto);
        when(service.getPatientById(1L)).thenReturn(Optional.of(patient));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(patientDto);

        //when & then
        mockMvc.perform(put("/lab/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateEmployee() throws Exception {
        //given
        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
        PatientDto patientDto = new PatientDto(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1");

        when(mapper.mapToPatient(patientDto)).thenReturn(patient);
        when(service.savePatient(patient)).thenReturn(patient);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(patientDto);

        //when & then
        mockMvc.perform(post("/lab/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}