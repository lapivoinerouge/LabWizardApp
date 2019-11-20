//package com.lab.wizard.controller;
//
//import com.google.gson.Gson;
//import com.lab.wizard.domain.user.Employee;
//import com.lab.wizard.domain.user.EmployeeDto;
//import com.lab.wizard.mapper.EmployeeMapper;
//import com.lab.wizard.service.EmployeeService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static java.util.Optional.ofNullable;
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(EmployeeController.class)
//public class EmployeeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private EmployeeMapper mapper;
//    @MockBean
//    private EmployeeService service;
//
//    @Test
//    public void shouldGetAllEmployees() throws Exception {
//        //given
//        List<EmployeeDto> list = new ArrayList<>();
//        list.add(new EmployeeDto(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user"));
//        list.add(new EmployeeDto(2L, "Firstname2", "Lastname2", "Licence2", "Login2", "Password2", "user"));
//
//        when(mapper.mapToEmployeeDtoList(service.getAllEmployees())).thenReturn(list);
//
//        //when & then
//        mockMvc.perform(get("/lab/employees/").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1L)))
//                .andExpect(jsonPath("$[0].firstname", is("Firstname1")))
//                .andExpect(jsonPath("$[1].licence", is("Licence2")));
//    }
//
//    @Test
//    public void shouldGetEmployeeById() throws Exception {
//        //given
//        Employee employee = new Employee(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");
//        EmployeeDto employeeDto = new EmployeeDto(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");
//        when(service.getEmployeeById(1L)).thenReturn(Optional.of(employee));
//        when(mapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);
//
//        //when & then
//        mockMvc.perform(get("/lab/employees/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.firstname", is("Firstname1")))
//                .andExpect(jsonPath("$.lastname", is("Lastname1")));
//    }
//
//    @Test
//    public void shouldDeleteEmployee() throws Exception {
//        //given
//        Employee employee = new Employee(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");
//        EmployeeDto employeeDto = new EmployeeDto(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");
//
//        when(service.getEmployeeById(1L)).thenReturn(ofNullable(employee));
//
//        //when & then
//        mockMvc.perform(delete("/lab/employees/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void shouldUpdateTask() throws Exception {
//        //given
//        Employee employee = new Employee(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");
//        EmployeeDto employeeDto = new EmployeeDto(1L, "Firstname1", "Lastname1", "Licence1", "Login1", "Password1", "user");
//
//        when(mapper.mapToEmployee(employeeDto)).thenReturn(employee);
//        when(service.saveEmployee(employee)).thenReturn(employee);
//        when(mapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);
//
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(employeeDto);
//
//        //when & then
//        mockMvc.perform(put("/lab/employees")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk());
//    }
//}