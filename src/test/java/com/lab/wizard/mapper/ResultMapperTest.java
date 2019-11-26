package com.lab.wizard.mapper;

import com.lab.wizard.domain.result.Result;
import com.lab.wizard.domain.result.ResultDto;
import com.lab.wizard.domain.result.UndoneResult;
import com.lab.wizard.domain.user.Employee;
import com.lab.wizard.domain.user.Patient;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResultMapperTest {

    private ResultMapper mapper = new ResultMapper();

    @Test
    public void testMapToTaskDto() {
        //given
        Patient patient = new Patient(1L, "firstname", "lastname", "pesel", "login", "password", null);
        UndoneResult undoneResult = new UndoneResult(1L, patient, "material", LocalDate.of(2019, 9, 10), false);
        Employee employee = new Employee(1L, "firstname", "lastname", "licence", "login", "password", "user");

        Result result = new Result(1L, undoneResult, "result", "comment", employee, LocalDate.now());

        //when
        ResultDto resultDto = mapper.mapToResultDto(result);

        //then
        assertEquals("firstname", resultDto.getFirstname());
        assertEquals("lastname", resultDto.getLastname());
        assertEquals("licence", resultDto.getEmployeeLicence());
        assertEquals(LocalDate.of(2019, 9, 10), resultDto.getReceiveDate());
        assertEquals(LocalDate.now(), resultDto.getFinishDate());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        List<Result> results = new ArrayList<>();

        Patient patient = new Patient(1L, "firstname", "lastname", "pesel", "login", "password", null);
        UndoneResult undoneResult1 = new UndoneResult(1L, patient, "material", LocalDate.of(2019, 9, 10), false);
        UndoneResult undoneResult2 = new UndoneResult(2L, patient, "material", LocalDate.of(2019, 9, 10), false);
        Employee employee = new Employee(1L, "firstname", "lastname", "licence", "login", "password", "user");

        results.add(new Result(1L, undoneResult1, "result", "comment", employee, LocalDate.now()));
        results.add(new Result(2L, undoneResult2, "result", "comment", employee, LocalDate.now()));

        //when
        List<ResultDto> resultDtos = mapper.mapToResultDtoList(results);

        //then
        assertEquals("result", resultDtos.get(0).getResult());
        assertEquals("pesel", resultDtos.get(1).getPesel());
        assertEquals(2L, resultDtos.get(1).getUndoneId().longValue());
    }
}