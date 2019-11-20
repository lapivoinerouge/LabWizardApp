package com.lab.wizard.repository;

import com.lab.wizard.domain.rating.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {

    @Override
    List<Rate> findAll();

    @Override
    Optional<Rate> findById(Long id);

    @Override
    Rate save(Rate rate);

    @Override
    void deleteById(Long id);
}
