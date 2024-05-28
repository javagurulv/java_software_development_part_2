package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.TCAgeCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TCAgeCoefficientRepository
        extends JpaRepository<TCAgeCoefficient, Long> {

    @Cacheable(cacheNames = {"tcAgeCoefficientCache"}, key = "#p0", unless="#result == null")
    @Query("SELECT ac from TCAgeCoefficient ac " +
            "where ac.ageFrom <= :age " +
            "and ac.ageTo >= :age")
    Optional<TCAgeCoefficient> findCoefficient(@Param("age") Integer age);

}
