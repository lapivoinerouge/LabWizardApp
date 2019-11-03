package com.lab.wizard.repository;

import com.lab.wizard.domain.result.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {


    @Override
    List<Result> findAll();

    @Override
    Optional<Result> findById(Long id);

    @Override
    Result save(Result result);

    @Override
    void deleteById(Long id);
}
