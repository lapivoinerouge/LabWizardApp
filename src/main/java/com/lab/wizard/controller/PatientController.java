package com.lab.wizard.controller;

import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.user.PatientDto;
import com.lab.wizard.mapper.PatientMapper;
import com.lab.wizard.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/patients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientMapper patientMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<PatientDto> getPatients() {
        return patientMapper.mapToPatientDtoList(patientService.getAllPatients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public PatientDto getPatient(@PathVariable Long id) throws NotFoundException {
        return patientMapper.mapToPatientDto(patientService.getPatientById(id).orElseThrow(() -> new NotFoundException(id)));
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/p/{pesel}")
//    public PatientDto getPatient(@PathVariable String pesel) throws NotFoundException {
//        return patientMapper.mapToPatientDto(patientService.getPatientByPesel(pesel).orElseThrow(() -> new NotFoundException(0L)));
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void addPatient(@RequestBody PatientDto patientDto) {
        patientService.savePatient(patientMapper.mapToPatient(patientDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public void editPatient(@RequestBody PatientDto patientDto) throws NotFoundException {
        if (patientService.getPatientById(patientDto.getId()).isPresent()) {
            patientMapper.mapToPatientDto(patientService.savePatient(patientMapper.mapToPatient(patientDto)));
        } else {
            throw new NotFoundException(patientDto.getId());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deletePatient(@PathVariable Long id) throws NotFoundException {
        try {
            patientService.deletePatient(id);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }
}
