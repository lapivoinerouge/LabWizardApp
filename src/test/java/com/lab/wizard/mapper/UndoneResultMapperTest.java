package com.lab.wizard.mapper;

import com.lab.wizard.domain.result.UndoneResult;
import com.lab.wizard.domain.result.UndoneResultDto;
import com.lab.wizard.domain.user.Patient;
import com.lab.wizard.service.PatientService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UndoneResultMapperTest {

    private UndoneResultMapper mapper = new UndoneResultMapper();

//    @Test
//    public void testMapToTask() throws Exception{
//        //given
//        UndoneResultDto undoneResultDto = new UndoneResultDto(1L, "firstname", "lastname", "pesel", "material", LocalDate.of(2019, 9, 10), false);
//
//        //when
//        UndoneResult undoneResult = mapper.mapToUndoneResult(undoneResultDto);
//
//        //then
//        assertEquals("firstname", undoneResult.getPatient().getFirstname());
//        assertEquals(LocalDate.of(2019, 9, 10), undoneResult.getReceiveDate());
//        assertEquals(null, undoneResult.getPatient().getUndoneResults());
//    }

    @Test
    public void testMapToTaskDto() {
        //given
        Patient patient = new Patient(1L, "firstname", "lastname", "pesel", "login", "password", null);
        UndoneResult undoneResult = new UndoneResult(1L, patient, "material", LocalDate.of(2019, 9, 10), false);

        //when
        UndoneResultDto undoneResultDto = mapper.mapToUndoneResultDto(undoneResult);

        //then
        assertEquals("pesel", undoneResultDto.getPesel());
        assertEquals(LocalDate.of(2019, 9, 10), undoneResultDto.getReceiveDate());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        List<UndoneResult> undoneResults = new ArrayList<>();
        Patient patient = new Patient(1L, "firstname", "lastname", "pesel", "login", "password", null);
        undoneResults.add(new UndoneResult(1L, patient, "material", LocalDate.of(2019, 9, 10), false));
        undoneResults.add(new UndoneResult(2L, patient, "material", LocalDate.of(2019, 9, 10), false));

        //when
        List<UndoneResultDto> undoneResultDtos = mapper.mapToUndoneResultDtoList(undoneResults);

        //then
        assertEquals(false, undoneResultDtos.get(0).isDone());
    }
}