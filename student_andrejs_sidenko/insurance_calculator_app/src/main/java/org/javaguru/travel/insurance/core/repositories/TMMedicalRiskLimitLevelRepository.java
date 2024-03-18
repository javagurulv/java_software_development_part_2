package org.javaguru.travel.insurance.core.repositories;

import java.util.Optional;
import org.javaguru.travel.insurance.core.domain.TMMedicalRiskLimitLevel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TMMedicalRiskLimitLevelRepository
        extends JpaRepository<TMMedicalRiskLimitLevel, Long> {

    @Cacheable(cacheNames = {"tmMedicalRiskLimitLevelCache"}, key = "#p0", unless = "#result == null")
    Optional<TMMedicalRiskLimitLevel> findByMedicalRiskLimitLevelIc(String medicalRiskLimitLevelIc);

}
