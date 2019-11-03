package com.lab.wizard.service;

import com.lab.wizard.domain.result.UndoneResult;
import com.lab.wizard.repository.UndoneResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UndoneResultService {

    @Autowired
    private UndoneResultRepository repository;

    public List<UndoneResult> getAllUndoneResults() {
        return repository.findAll();
    }

    public Optional<UndoneResult> getUndoneResultById(final Long id) {
        return repository.findById(id);
    }

    public UndoneResult saveUndoneResult(final UndoneResult undoneResult) {
        return repository.save(undoneResult);
    }

    public void deleteUndoneResult(final Long id) {
        repository.deleteById(id);
    }
}
