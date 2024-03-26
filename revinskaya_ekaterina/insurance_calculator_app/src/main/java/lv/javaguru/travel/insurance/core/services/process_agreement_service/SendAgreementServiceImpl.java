package lv.javaguru.travel.insurance.core.services.process_agreement_service;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.services.process_agreement_service.rabbit_mq_messages.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("mysql-container")
class SendAgreementServiceImpl implements SendAgreementService{
    @Autowired
    private MessageSender messageSender;

    @Override
    public void sendAgreement(AgreementDTO agreementDTO) {
        messageSender.sendAgreement(agreementDTO);
    }

    @Override
    public void sendAgreementXML(String filename) {
        messageSender.sendAgreementXML(filename);
    }
}
