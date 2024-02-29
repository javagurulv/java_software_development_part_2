package org.javaguru.travel.insurance.core.repositories;

import java.util.Optional;
import org.javaguru.travel.insurance.core.domain.TMAgeCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TMAgeCoefficientRepository
        extends JpaRepository<TMAgeCoefficient, Long> {

    @Cacheable(cacheNames = {"tmAgeCoefficientCache"}, key = "#p0", unless = "#result == null")
    @Query("SELECT ac from TMAgeCoefficient ac "
            + "where ac.ageFrom <= :age "
            + "and ac.ageTo >= :age")
    Optional<TMAgeCoefficient> findCoefficient(@Param("age") Integer age);

}
