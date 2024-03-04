package org.javaguru.travel.insurance.core.repositories.entities;

import java.util.List;
import org.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.javaguru.travel.insurance.core.domain.entities.SelectedRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SelectedRiskEntityRepository extends JpaRepository<SelectedRiskEntity, Long> {

    List<SelectedRiskEntity> findByAgreement(AgreementEntity agreement);

}
