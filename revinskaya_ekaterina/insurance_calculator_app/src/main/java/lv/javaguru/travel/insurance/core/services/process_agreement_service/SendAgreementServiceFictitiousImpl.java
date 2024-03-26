package lv.javaguru.travel.insurance.core.services.process_agreement_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.services.process_agreement_service.rabbit_mq_messages.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"!mysql-container"})
class SendAgreementServiceFictitiousImpl implements SendAgreementService {
    @Autowired
    private MessageSender messageSender;
    private static final Logger logger = LoggerFactory.getLogger(SendAgreementServiceFictitiousImpl.class);


    @Override
    public void sendAgreement(AgreementDTO agreementDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(agreementDTO);
            logger.info("there is no connection with RabbitMQ, can't send agreement " + json);

        } catch (JsonProcessingException e) {
            logger.error("there is no connection with RabbitMQ, can't process agreement to json");
        }
    }

    @Override
    public void sendAgreementXML(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("there is no connection with RabbitMQ, can't send file " + filename);
    }
}
