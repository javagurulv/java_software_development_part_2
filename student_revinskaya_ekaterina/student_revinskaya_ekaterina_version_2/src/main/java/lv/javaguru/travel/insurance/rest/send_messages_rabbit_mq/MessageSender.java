package lv.javaguru.travel.insurance.rest.send_messages_rabbit_mq;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbit;

    public void sendAgreement(AgreementDTO agreementDTO) {
        rabbit.convertAndSend("agreement.exchanger", "agreement", agreementDTO);
    }

}


