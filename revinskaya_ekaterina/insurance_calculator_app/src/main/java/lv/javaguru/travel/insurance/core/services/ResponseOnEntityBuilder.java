package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreResult;
import lv.javaguru.travel.insurance.core.domain.entity.*;
import lv.javaguru.travel.insurance.core.repositories.entity.*;
import lv.javaguru.travel.insurance.core.services.common.AgreementDTOConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ResponseOnEntityBuilder {
    @Autowired
    private AgreementRepository agreementRepository;
    @Autowired
    private PersonAgreementRepository personAgreementRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SelectedRiskRepository selectedRiskRepository;
    @Autowired
    private AgreementDTOConstructor agreementDTOConstructor;

    public TravelGetPolicyCoreResult buildResponse(String uuid) {
        TravelGetPolicyCoreResult result = new TravelGetPolicyCoreResult();
        AgreementEntity agreementEntity = agreementRepository.findByUuid(uuid)
                .orElseThrow(()->new RuntimeException(
                        "uuid "+ uuid+" not exist in the system"));
        List<SelectedRisk> risks = selectedRiskRepository.findByAgreementEntityId(agreementEntity);
        List<PersonAgreementEntity> personAgreementEntities = personAgreementRepository.findByAgreementEntityId(agreementEntity);
        result.setAgreement(agreementDTOConstructor.buildAgreementDTO(agreementEntity, risks, personAgreementEntities));
        return result;
    }


}
