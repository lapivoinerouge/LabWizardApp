package com.lab.wizard.controller;

import com.lab.wizard.config.AdminConfig;
import com.lab.wizard.controller.exception.NotFoundException;
import com.lab.wizard.domain.rating.RateDto;
import com.lab.wizard.email.Mail;
import com.lab.wizard.email.SimpleEmailService;
import com.lab.wizard.mapper.RateMapper;
import com.lab.wizard.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lab")
public class RateController {

    private static final String SUBJECT = "Nowa opinia";

    @Autowired
    private RateMapper mapper;
    @Autowired
    private RateService service;
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private AdminConfig adminConfig;

    @GetMapping(value = "/rates")
    public List<RateDto> getRates() {
        return mapper.mapToRateDtoList(service.getAllRates());
    }

    @GetMapping(value = "/rates/{id}")
    public RateDto getRate(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToRateDto(service.getRateById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @PostMapping(value = "/rates")
    public void addRate(@RequestBody RateDto rateDto) {
        service.saveRate(mapper.mapToRate(rateDto));
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(), SUBJECT, "Nowa opinia od użytkownika: " + rateDto.getName()
        ));
    }

    @PutMapping(value = "/rates")
    public void editRate(@RequestBody RateDto rateDto) throws NotFoundException {
        if (service.getRateById(rateDto.getId()).isPresent()) {
            mapper.mapToRateDto(service.saveRate(mapper.mapToRate(rateDto)));
        } else {
            throw new NotFoundException(rateDto.getId());
        }
    }

    @DeleteMapping(value = "/rates/{id}")
    public void deleteRate(@PathVariable Long id) throws NotFoundException {
        try {
            service.deleteRate(id);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }
}
