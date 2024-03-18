package lv.javaguru.travel.insurance.core.services.send_agreement_service;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.services.send_agreement_service.send_messages_rabbit_mq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
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
