package com.lab.wizard.repository;

import com.lab.wizard.domain.result.UndoneResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UndoneResultRepository extends CrudRepository<UndoneResult, Long> {

    @Override
    List<UndoneResult> findAll();

    @Override
    Optional<UndoneResult> findById(Long id);

    @Override
    UndoneResult save(UndoneResult undoneResult);

    @Override
    void deleteById(Long id);

    long countByDoneIsFalse();
}
