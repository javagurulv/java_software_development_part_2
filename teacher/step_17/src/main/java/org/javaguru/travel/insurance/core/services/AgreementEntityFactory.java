package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.entities.*;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementPersonEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementPersonRiskEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.SelectedRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class AgreementEntityFactory {

    @Autowired private AgreementEntityRepository agreementEntityRepository;
    @Autowired private PersonEntityFactory personEntityFactory;
    @Autowired private SelectedRiskEntityRepository selectedRiskEntityRepository;
    @Autowired private AgreementPersonEntityRepository agreementPersonEntityRepository;
    @Autowired private AgreementPersonRiskEntityRepository agreementPersonRiskEntityRepository;

    AgreementEntity createAgreementEntity(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = saveAgreement(agreementDTO);
        saveAllSelectedRisks(agreementDTO, agreementEntity);
        saveAllAgreementPersons(agreementDTO, agreementEntity);
        return agreementEntity;
    }

    private AgreementEntity saveAgreement(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setUuid(UUID.randomUUID().toString());
        agreementEntity.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        agreementEntity.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        agreementEntity.setCountry(agreementDTO.getCountry());
        agreementEntity.setAgreementPremium(agreementDTO.getAgreementPremium());
        return agreementEntityRepository.save(agreementEntity);
    }

    private void saveAllSelectedRisks(AgreementDTO agreementDTO,
                                      AgreementEntity agreementEntity) {
        agreementDTO.getSelectedRisks().forEach(riskIc -> {
            SelectedRiskEntity riskEntity = new SelectedRiskEntity();
            riskEntity.setAgreement(agreementEntity);
            riskEntity.setRiskIc(riskIc);
            selectedRiskEntityRepository.save(riskEntity);
        });
    }

    private void saveAllAgreementPersons(AgreementDTO agreementDTO,
                                         AgreementEntity agreementEntity) {
        agreementDTO.getPersons().forEach(personDTO -> {
            PersonEntity personEntity = personEntityFactory.createPersonEntity(personDTO);
            AgreementPersonEntity agreementPersonEntity = saveAgreementPerson(agreementEntity, personDTO, personEntity);
            saveAllPersonRisks(personDTO, agreementPersonEntity);
        });
    }

    private AgreementPersonEntity saveAgreementPerson(AgreementEntity agreementEntity, PersonDTO personDTO, PersonEntity personEntity) {
        AgreementPersonEntity agreementPersonEntity = new AgreementPersonEntity();
        agreementPersonEntity.setAgreement(agreementEntity);
        agreementPersonEntity.setPerson(personEntity);
        agreementPersonEntity.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        agreementPersonEntity.setTravelCost(personDTO.getTravelCost());
        agreementPersonEntity = agreementPersonEntityRepository.save(agreementPersonEntity);
        return agreementPersonEntity;
    }

    private void saveAllPersonRisks(PersonDTO personDTO,
                                    AgreementPersonEntity agreementPersonEntity) {
        personDTO.getRisks().forEach(riskDTO -> {
            AgreementPersonRiskEntity agreementPersonRiskEntity = new AgreementPersonRiskEntity();
            agreementPersonRiskEntity.setAgreementPerson(agreementPersonEntity);
            agreementPersonRiskEntity.setRiskIc(riskDTO.getRiskIc());
            agreementPersonRiskEntity.setPremium(riskDTO.getPremium());
            agreementPersonRiskEntityRepository.save(agreementPersonRiskEntity);
        });
    }

}
