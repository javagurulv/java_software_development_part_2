package lv.javaguru.travel.insurance.core.services.send_agreement_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;

import java.io.File;

public interface SendAgreementService {
    void sendAgreement(AgreementDTO agreementDTO);
    void sendAgreementXML(String filename);
}
