package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.TMCountryDefaultDayRate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TMCountryDefaultDayRateRepository
        extends JpaRepository<TMCountryDefaultDayRate, Long> {

    @Cacheable(cacheNames = {"tmCountryDefaultDayRateCache"}, key = "#p0", unless= "#result == null")
    Optional<TMCountryDefaultDayRate> findByCountryIc(String countryIc);

}
