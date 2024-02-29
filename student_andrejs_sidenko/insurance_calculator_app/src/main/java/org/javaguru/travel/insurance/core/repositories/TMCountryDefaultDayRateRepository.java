package org.javaguru.travel.insurance.core.repositories;

import java.util.Optional;
import org.javaguru.travel.insurance.core.domain.TMCountryDefaultDayRate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TMCountryDefaultDayRateRepository
        extends JpaRepository<TMCountryDefaultDayRate, Long> {

    @Cacheable(cacheNames = {"tmCountryDefaultDayRateCache"}, key = "#p0", unless = "#result == null")
    Optional<TMCountryDefaultDayRate> findByCountryIc(String countryIc);

}
