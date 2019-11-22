package com.lab.wizard.controller;

import com.google.gson.Gson;
import com.lab.wizard.config.AdminConfig;
import com.lab.wizard.domain.rating.Rate;
import com.lab.wizard.domain.rating.RateDto;
import com.lab.wizard.email.SimpleEmailService;
import com.lab.wizard.mapper.RateMapper;
import com.lab.wizard.service.RateService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RateController.class)
public class RateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RateService service;
    @MockBean
    private RateMapper mapper;
    @MockBean
    private SimpleEmailService emailService;
    @MockBean
    private AdminConfig adminConfig;

    @Test
    public void shouldGetAllRates() throws Exception {
        //given
        List<RateDto> list = new ArrayList<>();
        list.add(new RateDto(1L, "Name1", 3L, "Comment1"));
        list.add(new RateDto(2L, "Name2", 4L, "Comment2"));

        when(mapper.mapToRateDtoList(service.getAllRates())).thenReturn(list);

        //when & then
        mockMvc.perform(get("/lab/rates").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Name1")))
                .andExpect(jsonPath("$[1].rate", is(4)));
    }

    @Test
    public void shouldGetRateById() throws Exception {
        //given
        Rate rate = new Rate(1L, "Name1", 3L, "Comment1");
        RateDto rateDto = new RateDto(1L, "Name1", 3L, "Comment1");
        when(service.getRateById(1L)).thenReturn(Optional.of(rate));
        when(mapper.mapToRateDto(rate)).thenReturn(rateDto);

        //when & then
        mockMvc.perform(get("/lab/rates/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Name1")))
                .andExpect(jsonPath("$.rate", is(3)));
    }

    @Test
    public void shouldDeletePatient() throws Exception {
        //given
        Rate rate = new Rate(1L, "Name1", 3L, "Comment1");

        when(service.getRateById(1L)).thenReturn(ofNullable(rate));

        //when & then
        mockMvc.perform(delete("/lab/rates/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldEditRate() throws Exception {
        //given
        Rate rate = new Rate(1L, "Name1", 3L, "Comment1");
        RateDto rateDto = new RateDto(1L, "Name1", 3L, "Comment1");

        when(mapper.mapToRate(rateDto)).thenReturn(rate);
        when(service.saveRate(rate)).thenReturn(rate);
        when(mapper.mapToRateDto(rate)).thenReturn(rateDto);
        when(service.getRateById(1L)).thenReturn(Optional.of(rate));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rateDto);

        //when & then
        mockMvc.perform(put("/lab/rates")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateRate() throws Exception {
        //given
        Rate rate = new Rate(1L, "Name1", 3L, "Comment1");
        RateDto rateDto = new RateDto(1L, "Name1", 3L, "Comment1");

        when(mapper.mapToRate(rateDto)).thenReturn(rate);
        when(service.saveRate(rate)).thenReturn(rate);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rateDto);

        //when & then
        mockMvc.perform(post("/lab/rates")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}