package com.lab.wizard.service;

import com.lab.wizard.domain.rating.Rate;
import com.lab.wizard.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    @Autowired
    private RateRepository repository;

    public List<Rate> getAllRates() {
        return repository.findAll();
    }

    public Optional<Rate> getRateById(final Long id) {
        return repository.findById(id);
    }

    public Rate saveRate(final Rate rate) {
        return repository.save(rate);
    }

    public void deleteRate(final Long id) {
        repository.deleteById(id);
    }
}
