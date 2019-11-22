package com.lab.wizard.controller;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.result.ResultDto;
import com.lab.wizard.domain.result.UndoneResult;
import com.lab.wizard.mapper.ResultMapper;
import com.lab.wizard.service.EmployeeService;
import com.lab.wizard.service.ResultService;
import com.lab.wizard.service.UndoneResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lab")
public class ResultController {

    @Autowired
    private ResultMapper mapper;
    @Autowired
    private ResultService service;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UndoneResultService undoneResultService;

    @GetMapping(value = "/results")
    public List<ResultDto> getResultList() {
        return mapper.mapToResultDtoList(service.getAllResults());
    }

    @GetMapping(value = "/results/p/{pesel}")
    public List<ResultDto> getResultList(@PathVariable String pesel) {
        return mapper.mapToResultDtoList(service.getAllByPesel(pesel));
    }

    @GetMapping(value = "/results/{id}")
    public ResultDto getResult(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToResultDto(service.getResultById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @PostMapping(value = "/results")
    public void addResult(@RequestBody ResultDto resultDto) throws NotFoundException {
        Long undoneId = resultDto.getUndoneId();
        Long employeeId = employeeService.getEmployeeByLicence(resultDto.getEmployeeLicence()).get().getId();

        Optional<UndoneResult> undoneResult = undoneResultService.getUndoneResultById(resultDto.getUndoneId());

        boolean undone = undoneResultService.getUndoneResultById(undoneId).isPresent();
        boolean employee = employeeService.getEmployeeById(employeeId).isPresent();

        if(undone && employee) {
            service.saveResult(mapper.mapToResult(resultDto));
            undoneResultService.saveUndoneResult(new UndoneResult(
                    undoneResult.get().getId(),
                    undoneResult.get().getPatient(),
                    undoneResult.get().getMaterial(),
                    undoneResult.get().getReceiveDate(),
                    true));
        } else {
            throw new NotFoundException(!undone? undoneId : employeeId);
        }
    }

    @PutMapping(value = "/results")
    public void editResult(@RequestBody ResultDto resultDto) throws NotFoundException {
        Long undoneId = resultDto.getUndoneId();
        Long employeeId = employeeService.getEmployeeByLicence(resultDto.getEmployeeLicence()).get().getId();
        Long resultId = resultDto.getId();

        boolean undone = undoneResultService.getUndoneResultById(undoneId).isPresent();
        boolean employee = employeeService.getEmployeeById(employeeId).isPresent();
        boolean result = service.getResultById(resultId).isPresent();


        if (undone && result && employee) {
            mapper.mapToResultDto(service.saveResult(mapper.mapToResult(resultDto)));
        } else {
            throw new NotFoundException(!undone? undoneId : employee? employeeId : resultId);
        }
    }

    @DeleteMapping(value = "/results/{id}")
    public void deleteResult(@PathVariable Long id) throws NotFoundException {
        try {
            service.deleteResult(id);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }

}
