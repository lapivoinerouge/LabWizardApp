package com.lab.wizard.service;

import com.lab.wizard.domain.result.Result;
import com.lab.wizard.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    private ResultRepository repository;

    public List<Result> getAllResults() {
        return repository.findAll();
    }

    public List<Result> getAllByPesel(final String pesel) {
        List<Result> listOfAll = repository.findAll();
        List<Result> results = listOfAll.stream()
                .filter(result -> result.getUndoneResult().getPatient().getPesel().equals(pesel))
                .collect(Collectors.toList());
        return results;
    }

    public Optional<Result> getResultById(final Long id) {
        return repository.findById(id);
    }

    public Result saveResult(final Result result) {
        return repository.save(result);
    }

    public void deleteResult(final Long id) {
        repository.deleteById(id);
    }

}
