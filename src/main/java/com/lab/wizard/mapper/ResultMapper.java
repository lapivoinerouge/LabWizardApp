package com.lab.wizard.mapper;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.result.Result;
import com.lab.wizard.domain.result.ResultDto;
import com.lab.wizard.service.EmployeeService;
import com.lab.wizard.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultMapper {

    @Autowired
    private PatientService patientService;
    @Autowired
    private EmployeeService employeeService;

    public Result mapToResult(final ResultDto dto) throws NotFoundException {
        return new Result(
                dto.getId(),
                patientService.getPatientByPesel(dto.getPatientPesel()).orElseThrow(() -> new NotFoundException(0L)),
                dto.getMaterial(),
                dto.getReceiveDate(),
                dto.getResult(),
                dto.getComment(),
                employeeService.getEmployeeByLicence(dto.getEmployeeLicence()).orElseThrow(() -> new NotFoundException(0L)),
                dto.getFinishDate()
        );
    }

    public ResultDto mapToResultDto(final Result result) {
        return new ResultDto(
                result.getId(),
                result.getPatient().getPesel(),
                result.getMaterial(),
                result.getReceiveDate(),
                result.getResult(),
                result.getComment(),
                result.getEmployee().getLicence(),
                result.getFinishDate()
        );
    }


    public List<ResultDto> mapToResultDtoList(final List<Result> results) {
        return results.stream()
                .map(r -> new ResultDto(r.getId(), r.getPatient().getPesel(), r.getMaterial(), r.getReceiveDate(), r.getResult(), r.getComment(), r.getEmployee().getLicence(), r.getFinishDate()))
                .collect(Collectors.toList());
    }
}
