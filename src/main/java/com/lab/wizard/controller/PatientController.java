package com.lab.wizard.controller;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.user.PatientDto;
import com.lab.wizard.mapper.PatientMapper;
import com.lab.wizard.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lab")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientMapper patientMapper;

    @GetMapping(value = "/patients")
    public List<PatientDto> getPatients() {
        return patientMapper.mapToPatientDtoList(patientService.getAllPatients());
    }

    @GetMapping(value = "/patients/{id}")
    public PatientDto getPatient(@PathVariable Long id) throws NotFoundException {
        return patientMapper.mapToPatientDto(patientService.getPatientById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @PostMapping(value = "/patients")
    public void addPatient(@RequestBody PatientDto patientDto) {
        patientService.savePatient(patientMapper.mapToPatient(patientDto));
    }

    @PutMapping(value = "/patients")
    public void editPatient(@RequestBody PatientDto patientDto) throws NotFoundException {
        if (patientService.getPatientById(patientDto.getId()).isPresent()) {
            patientMapper.mapToPatientDto(patientService.savePatient(patientMapper.mapToPatient(patientDto)));
        } else {
            throw new NotFoundException(patientDto.getId());
        }
    }

    @DeleteMapping(value = "/patients/{id}")
    public void deletePatient(@PathVariable Long id) throws NotFoundException {
        try {
            patientService.deletePatient(id);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }
}
