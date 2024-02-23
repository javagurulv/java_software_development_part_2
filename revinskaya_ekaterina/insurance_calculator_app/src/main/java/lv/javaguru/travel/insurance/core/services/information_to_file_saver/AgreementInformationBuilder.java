package lv.javaguru.travel.insurance.core.services.information_to_file_saver;

import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.entity.*;
import lv.javaguru.travel.insurance.core.repositories.entity.*;
import lv.javaguru.travel.insurance.core.services.common.AgreementDTOConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgreementInformationBuilder {
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

    public AgreementDTO getAgreementDTOById(Long id) {
        AgreementEntity agreementEntity = agreementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "agreement with id " + id + " not found"));
        List<SelectedRisk> risks = selectedRiskRepository.findByAgreementEntityId(agreementEntity);
        List<PersonAgreementEntity> personAgreementEntities = personAgreementRepository.findByAgreementEntityId(agreementEntity);
        return agreementDTOConstructor.buildAgreementDTO(agreementEntity, risks, personAgreementEntities);
    }


}
