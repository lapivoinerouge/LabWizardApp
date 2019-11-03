package com.lab.wizard.controller;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.result.UndoneResultDto;
import com.lab.wizard.mapper.UndoneResultMapper;
import com.lab.wizard.service.UndoneResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lab/undone", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UndoneResultController {

    @Autowired
    private UndoneResultService service;
    @Autowired
    private UndoneResultMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<UndoneResultDto> getUndoneList() {
        return mapper.mapToUndoneResultDtoList(service.getAllUndoneResults());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UndoneResultDto getUndoneResult(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToUndoneResultDto(service.getUndoneResultById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void addUndoneResult(@RequestBody UndoneResultDto undoneResultDto) throws NotFoundException {
        service.saveUndoneResult(mapper.mapToUndoneResult(undoneResultDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public void editUndoneResult(@RequestBody UndoneResultDto undoneResultDto) throws NotFoundException {
        if (service.getUndoneResultById(undoneResultDto.getId()).isPresent()) {
            mapper.mapToUndoneResultDto(service.saveUndoneResult(mapper.mapToUndoneResult(undoneResultDto)));
        } else {
            throw new NotFoundException(undoneResultDto.getId());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUndoneResult(@PathVariable Long id) throws NotFoundException {
        try {
            service.deleteUndoneResult(id);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }
}
