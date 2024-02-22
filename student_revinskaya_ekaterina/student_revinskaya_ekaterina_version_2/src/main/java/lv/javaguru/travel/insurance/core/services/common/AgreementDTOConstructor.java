package lv.javaguru.travel.insurance.core.services.common;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.entity.*;
import lv.javaguru.travel.insurance.core.repositories.entity.PersonAgreementRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgreementDTOConstructor {
    @Autowired
    private PersonAgreementRiskRepository personAgreementRiskRepository;
    public AgreementDTO buildAgreementDTO(AgreementEntity agreementEntity, List<SelectedRisk> risks, List<PersonAgreementEntity> personAgreementEntities) {
        return AgreementDTO.builder()
                .uuid(agreementEntity.getUuid())
                .travelCost(agreementEntity.getTravelCost())
                .agreementDateFrom(agreementEntity.getDateFrom())
                .agreementDateTo(agreementEntity.getDateTo())
                .selectedRisks(selectedRisksListFromEntity(risks))
                .country(agreementEntity.getCountry())
                .agreementPremium(agreementEntity.getPremium())
                .persons(listPersonDTOFromEntityList(personAgreementEntities))
                .build();
    }

    private List<PersonDTO> listPersonDTOFromEntityList(List<PersonAgreementEntity> personAgreementEntities) {
        return personAgreementEntities.stream()
                .map(personAgreement -> personDTOFromPersonEntity(personAgreement.getPersonEntityId(), personAgreement))
                .collect(Collectors.toList());
    }

    private PersonDTO personDTOFromPersonEntity(PersonEntity personEntity, PersonAgreementEntity personAgreementEntity) {
        return PersonDTO.builder()
                .personalCode(personEntity.getPersonalCode())
                .personFirstName(personEntity.getFirstName())
                .personLastName(personEntity.getLastName())
                .personBirthDate(personEntity.getBirthday())
                .medicalRiskLimitLevel(personAgreementEntity.getMedicalRiskLimitLevel())
                .risks(listRiskRTO(personAgreementRiskRepository.findByPersonAgreementEntityId(personAgreementEntity)))
                .build();
    }

    private List<RiskDTO> listRiskRTO(List<PersonAgreementRiskEntity> personAgreementRiskEntities) {
        return personAgreementRiskEntities.stream()
                .map(personAgreementRiskEntity -> new RiskDTO(
                        personAgreementRiskEntity.getRiskIc(), personAgreementRiskEntity.getPremium()))
                .collect(Collectors.toList());
    }

    private List<String> selectedRisksListFromEntity(List<SelectedRisk> selectedRiskEntities) {
        return selectedRiskEntities.stream()
                .map(SelectedRisk::getRiskIc)
                .collect(Collectors.toList());
    }
}
