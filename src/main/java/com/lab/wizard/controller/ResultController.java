package com.lab.wizard.controller;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.result.ResultDto;
import com.lab.wizard.mapper.ResultMapper;
import com.lab.wizard.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lab/results", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ResultController {

    @Autowired
    private ResultMapper mapper;
    @Autowired
    private ResultService service;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<ResultDto> getResultList() {
        return mapper.mapToResultDtoList(service.getAllResults());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultDto getResult(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToResultDto(service.getResultById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void addResult(@RequestBody ResultDto resultDto) throws NotFoundException {
        service.saveResult(mapper.mapToResult(resultDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public void editResult(@RequestBody ResultDto resultDto) throws NotFoundException {
        if (service.getResultById(resultDto.getId()).isPresent()) {
            mapper.mapToResultDto(service.saveResult(mapper.mapToResult(resultDto)));
        } else {
            throw new NotFoundException(resultDto.getId());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteResult(@PathVariable Long id) throws NotFoundException {
        try {
            service.deleteResult(id);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }
}
