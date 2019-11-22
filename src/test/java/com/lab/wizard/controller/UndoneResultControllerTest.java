package com.lab.wizard.controller;


import com.google.gson.Gson;
import com.lab.wizard.domain.result.UndoneResult;
import com.lab.wizard.domain.result.UndoneResultDto;
import com.lab.wizard.domain.user.Patient;
import com.lab.wizard.mapper.UndoneResultMapper;
import com.lab.wizard.service.UndoneResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
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
@WebMvcTest(UndoneResultController.class)
public class UndoneResultControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UndoneResultService service;
    @MockBean
    private UndoneResultMapper mapper;

    @Test
    public void shouldGetAllUndone() throws Exception {
        //given
        List<UndoneResultDto> list = new ArrayList<>();
        list.add(new UndoneResultDto(1L, "Firstname1", "Lastname1", "Pesel1", "Material1", LocalDate.of(2019, 11, 11), false));
        list.add(new UndoneResultDto(2L, "Firstname2", "Lastname2", "Pesel2", "Material2", LocalDate.of(2019, 11, 21), false));

        when(mapper.mapToUndoneResultDtoList(service.getAllUndoneResults())).thenReturn(list);

        //when & then
        mockMvc.perform(get("/lab/undone").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstname", is("Firstname1")))
                .andExpect(jsonPath("$[1].done", is(false)));
    }

    @Test
    public void shouldGetUndoneById() throws Exception {
        //given
        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
        UndoneResult undoneResult = new UndoneResult(1L, patient, "Material1", LocalDate.of(2019, 11, 11), false);
        UndoneResultDto undoneResultDto = new UndoneResultDto(1L, "Firstname1", "Lastname1", "Pesel1", "Material1", LocalDate.of(2019, 11, 11), false);

        when(service.getUndoneResultById(1L)).thenReturn(Optional.of(undoneResult));
        when(mapper.mapToUndoneResultDto(undoneResult)).thenReturn(undoneResultDto);

        //when & then
        mockMvc.perform(get("/lab/undone/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is("Firstname1")))
                .andExpect(jsonPath("$.done", is(false)));
    }

    @Test
    public void shouldDeleteUndone() throws Exception {
        //given
        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
        UndoneResult undoneResult = new UndoneResult(1L, patient, "Material1", LocalDate.of(2019, 11, 11), false);

        when(service.getUndoneResultById(1L)).thenReturn(ofNullable(undoneResult));

        //when & then
        mockMvc.perform(delete("/lab/undone/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    public void shouldEditUndone() throws Exception {
//        //given
//        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
//        UndoneResult undoneResult = new UndoneResult(1L, patient, "Material1", LocalDate.of(2019, 11, 11), false);
//        UndoneResultDto undoneResultDto = new UndoneResultDto(1L, patient.getFirstname(), patient.getLastname(), patient.getPesel(), "Material1", LocalDate.of(2019, 11, 11), false);
//
//        when(mapper.mapToUndoneResult(undoneResultDto)).thenReturn(undoneResult);
//        when(service.saveUndoneResult(undoneResult)).thenReturn(undoneResult);
//        when(mapper.mapToUndoneResultDto(undoneResult)).thenReturn(undoneResultDto);
//        when(service.getUndoneResultById(1L)).thenReturn(Optional.of(undoneResult));
//
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(undoneResultDto);
//
//        //when & then
//        mockMvc.perform(put("/lab/undone")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void shouldCreateUndone() throws Exception {
//        //given
//        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
//        UndoneResult undoneResult = new UndoneResult(1L, patient, "Material1", LocalDate.of(2019, 11, 11), false);
//        UndoneResultDto undoneResultDto = new UndoneResultDto(1L, "Firstname1", "Lastname1", "Pesel1", "Material1", LocalDate.of(2019, 11, 11), false);
//
//        when(mapper.mapToUndoneResult(undoneResultDto)).thenReturn(undoneResult);
//        when(service.saveUndoneResult(undoneResult)).thenReturn(undoneResult);
//
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(undoneResultDto);
//
//        //when & then
//        mockMvc.perform(post("/lab/undone")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk());
//    }
}