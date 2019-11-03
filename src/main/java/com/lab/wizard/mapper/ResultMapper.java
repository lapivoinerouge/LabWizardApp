package com.lab.wizard.mapper;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.result.Result;
import com.lab.wizard.domain.result.ResultDto;
import com.lab.wizard.service.EmployeeService;
import com.lab.wizard.service.UndoneResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultMapper {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UndoneResultService undoneResultService;

    public Result mapToResult(final ResultDto dto) throws NotFoundException {
        return new Result(
                dto.getId(),
                undoneResultService.getUndoneResultById(dto.getUndoneId()).orElseThrow(() -> new NotFoundException(dto.getUndoneId())),
                dto.getResult(),
                dto.getComment(),
                employeeService.getEmployeeByLicence(dto.getEmployeeLicence()).orElseThrow(() -> new NotFoundException(0L)),
                dto.getFinishDate()
        );
    }

    public ResultDto mapToResultDto(final Result result) {
        return new ResultDto(
                result.getId(),
                result.getUndoneResult().getId(),
                result.getResult(),
                result.getComment(),
                result.getEmployee().getLicence(),
                result.getFinishDate()
        );
    }


    public List<ResultDto> mapToResultDtoList(final List<Result> results) {
        return results.stream()
                .map(r -> new ResultDto(r.getId(), r.getUndoneResult().getId(), r.getResult(), r.getComment(), r.getEmployee().getLicence(), r.getFinishDate()))
                .collect(Collectors.toList());
    }
}
