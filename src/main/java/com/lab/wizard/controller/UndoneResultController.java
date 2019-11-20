package com.lab.wizard.controller;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.result.UndoneResultDto;
import com.lab.wizard.mapper.UndoneResultMapper;
import com.lab.wizard.service.UndoneResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lab")
public class UndoneResultController {

    @Autowired
    private UndoneResultService service;
    @Autowired
    private UndoneResultMapper mapper;

    @GetMapping(value = "/undone")
    public List<UndoneResultDto> getUndoneList() {
        return mapper.mapToUndoneResultDtoList(service.getAllUndoneResults());
    }

    @GetMapping(value = "/undone/{id}")
    public UndoneResultDto getUndoneResult(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToUndoneResultDto(service.getUndoneResultById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @PostMapping(value = "/undone")
    public void addUndoneResult(@RequestBody UndoneResultDto undoneResultDto) throws NotFoundException {
        service.saveUndoneResult(mapper.mapToUndoneResult(undoneResultDto));
    }

    @PutMapping(value = "/undone")
    public void editUndoneResult(@RequestBody UndoneResultDto undoneResultDto) throws NotFoundException {
        if (service.getUndoneResultById(undoneResultDto.getId()).isPresent()) {
            mapper.mapToUndoneResultDto(service.saveUndoneResult(mapper.mapToUndoneResult(undoneResultDto)));
        } else {
            throw new NotFoundException(undoneResultDto.getId());
        }
    }

    @DeleteMapping(value = "/undone/{id}")
    public void deleteUndoneResult(@PathVariable Long id) throws NotFoundException {
        try {
            service.deleteUndoneResult(id);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }
}
