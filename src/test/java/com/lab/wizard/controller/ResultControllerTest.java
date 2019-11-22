package com.lab.wizard.controller;

import com.google.gson.Gson;
import com.lab.wizard.domain.result.Result;
import com.lab.wizard.domain.result.ResultDto;
import com.lab.wizard.domain.result.UndoneResult;
import com.lab.wizard.domain.user.Employee;
import com.lab.wizard.domain.user.Patient;
import com.lab.wizard.mapper.ResultMapper;
import com.lab.wizard.service.EmployeeService;
import com.lab.wizard.service.ResultService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ResultController.class)
public class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ResultService service;
    @MockBean
    private ResultMapper mapper;
    @MockBean
    private UndoneResultService undoneResultService;
    @MockBean
    private EmployeeService employeeService;

    @Test
    public void shouldGetAllResults() throws Exception {
        //given
        List<ResultDto> list = new ArrayList<>();
        list.add(new ResultDto(1L, 1L, "Firstname1", "Lastname1", "Pesel1", "Material1", LocalDate.of(2019, 11, 11), "Result1", "Comment1", "EmployeeLicence1", LocalDate.now()));
        list.add(new ResultDto(2L, 2L, "Firstname2", "Lastname2", "Pesel2", "Material2", LocalDate.of(2019, 11, 11), "Result2", "Comment2", "EmployeeLicence2", LocalDate.now()));

        when(mapper.mapToResultDtoList(service.getAllResults())).thenReturn(list);

        //when & then
        mockMvc.perform(get("/lab/results").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstname", is("Firstname1")))
                .andExpect(jsonPath("$[1].result", is("Result2")));
    }

    @Test
    public void shouldGetResultById() throws Exception {
        //given
        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
        UndoneResult undoneResult = new UndoneResult(1L, patient, "Material1", LocalDate.of(2019, 11, 11), false);
        Employee employee = new Employee(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");

        Result result = new Result(1L, undoneResult, "Result1", "Comment1", employee, LocalDate.now());
        ResultDto resultDto = new ResultDto(1L, 1L, "Firstname1", "Lastname1", "Pesel1", "Material1", LocalDate.of(2019, 11, 11), "Result1", "Comment1", "EmployeeLicence1", LocalDate.now());

        when(service.getResultById(1L)).thenReturn(Optional.of(result));
        when(mapper.mapToResultDto(result)).thenReturn(resultDto);

        //when & then
        mockMvc.perform(get("/lab/results/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment", is("Comment1")))
                .andExpect(jsonPath("$.result", is("Result1")));
    }

    @Test
    public void shouldDeletePatient() throws Exception {
        //given
        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
        UndoneResult undoneResult = new UndoneResult(1L, patient, "Material1", LocalDate.of(2019, 11, 11), false);
        Employee employee = new Employee(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");

        Result result = new Result(1L, undoneResult, "Result1", "Comment1", employee, LocalDate.now());
        ResultDto resultDto = new ResultDto(1L, 1L, "Firstname1", "Lastname1", "Pesel1", "Material1", LocalDate.of(2019, 11, 11), "Result1", "Comment1", "EmployeeLicence1", LocalDate.now());

        when(service.getResultById(1L)).thenReturn(ofNullable(result));

        //when & then
        mockMvc.perform(delete("/lab/results/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    public void shouldCreateResult() throws Exception {
//        //given
//        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
//        UndoneResult undoneResult = new UndoneResult(1L, patient, "Material1", LocalDate.of(2019, 11, 11), false);
//        Employee employee = new Employee(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");
//
//        Result result = new Result(1L, undoneResult, "Result1", "Comment1", employee, LocalDate.now());
//        ResultDto resultDto = new ResultDto(1L, 1L, "Firstname1", "Lastname1", "Pesel1", "Material1", LocalDate.of(2019, 11, 11), "Result1", "Comment1", "EmployeeLicence1", LocalDate.now());
//
//        when(undoneResultService.getUndoneResultById(1L)).thenReturn(Optional.of(undoneResult));
//        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee));
//
//        when(mapper.mapToResult(resultDto)).thenReturn(result);
//        when(service.saveResult(result)).thenReturn(result);
//
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(resultDto);
//
//        //when & then
//        mockMvc.perform(post("/lab/results")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void shouldEditUndone() throws Exception {
//        //given
//        Patient patient = new Patient(1L, "Firstname1", "Lastname1", "Pesel1", "Email1", "Password1", null);
//        UndoneResult undoneResult = new UndoneResult(1L, patient, "Material1", LocalDate.of(2019, 11, 11), false);
//        Employee employee = new Employee(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");
//
//        Result result = new Result(1L, undoneResult, "Result1", "Comment1", employee, LocalDate.now());
//        ResultDto resultDto = new ResultDto(1L, 1L, "Firstname1", "Lastname1", "Pesel1", "Material1", LocalDate.of(2019, 11, 11), "Result1", "Comment1", "EmployeeLicence1", LocalDate.now());
//
//        when(mapper.mapToUndoneResult(undoneResultDto)).thenReturn(undoneResult);
//        when(service.saveUndoneResult(undoneResult)).thenReturn(undoneResult);
//        when(mapper.mapToUndoneResultDto(undoneResult)).thenReturn(undoneResultDto);
//        when(service.getUndoneResultById(1L)).thenReturn(Optional.of(undoneResult));
//
//        when(undoneResultService.getUndoneResultById(1L)).thenReturn(Optional.of(undoneResult));
//        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee));
//
//        when(mapper.mapToResult(resultDto)).thenReturn(result);
//        when(service.saveResult(result)).thenReturn(result);
//
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(resultDto);
//        //when & then
//        mockMvc.perform(put("/lab/results")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk());
//    }
}