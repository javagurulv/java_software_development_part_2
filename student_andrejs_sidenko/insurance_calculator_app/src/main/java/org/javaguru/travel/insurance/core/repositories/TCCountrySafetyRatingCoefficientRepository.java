package org.javaguru.travel.insurance.core.repositories;

import java.util.Optional;
import org.javaguru.travel.insurance.core.domain.TCCountrySafetyRatingCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TCCountrySafetyRatingCoefficientRepository
        extends JpaRepository<TCCountrySafetyRatingCoefficient, Long> {

    @Cacheable(cacheNames = {"tcCountrySafetyRatingCache"}, key = "#p0", unless = "#result == null")
    Optional<TCCountrySafetyRatingCoefficient> findByCountryIc(String countryIc);

}
