package lv.javaguru.travel.insurance.core.services.process_agreement_service.rabbit_mq_messages;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbit;

    public void sendAgreement(AgreementDTO agreementDTO) {
       rabbit.convertAndSend("agreement.exchanger", "agreement", agreementDTO);
    }
    public void sendAgreementXML(String filename) {
       rabbit.convertAndSend("agreement.exchanger", "agreementXML", new File(filename));
    }

}


