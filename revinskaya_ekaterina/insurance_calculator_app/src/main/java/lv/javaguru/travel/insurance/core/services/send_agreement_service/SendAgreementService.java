package lv.javaguru.travel.insurance.core.services.send_agreement_service;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;

public interface SendAgreementService {
    void sendAgreement(AgreementDTO agreementDTO);
}
