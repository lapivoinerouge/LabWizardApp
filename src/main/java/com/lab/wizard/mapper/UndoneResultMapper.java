package com.lab.wizard.mapper;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.result.UndoneResult;
import com.lab.wizard.domain.result.UndoneResultDto;
import com.lab.wizard.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UndoneResultMapper {

    @Autowired
    PatientService patientService;

    public UndoneResult mapToUndoneResult(final UndoneResultDto dto) throws NotFoundException{
        return new UndoneResult(
                dto.getId(),
                patientService.getPatientByPesel(dto.getPesel()).orElseThrow(() -> new NotFoundException(0L)),
                dto.getMaterial(),
                dto.getReceiveDate(),
                dto.isDone());
    }

    public UndoneResultDto mapToUndoneResultDto(final UndoneResult undoneResult) {
        return new UndoneResultDto(
                undoneResult.getId(),
                undoneResult.getPatient().getFirstname(),
                undoneResult.getPatient().getLastname(),
                undoneResult.getPatient().getPesel(),
                undoneResult.getMaterial(),
                undoneResult.getReceiveDate(),
                undoneResult.isDone());
    }

    public List<UndoneResultDto> mapToUndoneResultDtoList(final List<UndoneResult> undoneResults) {
        return undoneResults.stream()
                .map(u -> new UndoneResultDto(u.getId(), u.getPatient().getFirstname(), u.getPatient().getLastname(), u.getPatient().getPesel(), u.getMaterial(), u.getReceiveDate(), u.isDone()))
                .collect(Collectors.toList());
    }
}
