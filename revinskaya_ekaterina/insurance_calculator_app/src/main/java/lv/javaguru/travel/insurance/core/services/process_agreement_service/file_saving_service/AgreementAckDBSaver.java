package lv.javaguru.travel.insurance.core.services.process_agreement_service.file_saving_service;

import lv.javaguru.travel.insurance.core.domain.entity.AgreementAckEntity;
import lv.javaguru.travel.insurance.core.repositories.entity.AgreementAckRepository;
import lv.javaguru.travel.insurance.core.services.process_agreement_service.file_saving_service.dto.SaveFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgreementAckDBSaver {
    @Autowired
    private AgreementAckRepository agreementAckRepository;
    public void saveAgreementAckResponse(SaveFileResponse saveFileResponse){
        AgreementAckEntity agreementAckEntity = new AgreementAckEntity();
        agreementAckEntity.setAlreadyExported(true);
        agreementAckEntity.setFilePath(saveFileResponse.getFilePath());
        agreementAckEntity.setAgreementUuid(saveFileResponse.getAgreementUuid());
        agreementAckRepository.save(agreementAckEntity);
    }

}
