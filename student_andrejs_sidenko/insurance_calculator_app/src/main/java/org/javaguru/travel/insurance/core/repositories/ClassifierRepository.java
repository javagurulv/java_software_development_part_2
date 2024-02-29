package org.javaguru.travel.insurance.core.repositories;

import java.util.Optional;
import org.javaguru.travel.insurance.core.domain.Classifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClassifierRepository extends JpaRepository<Classifier, Long> {

    @Cacheable(cacheNames = {"classifierCache"}, key = "#p0", unless = "#result == null")
    Optional<Classifier> findByTitle(String title);

}
