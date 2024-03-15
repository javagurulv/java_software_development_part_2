package org.javaguru.doc.generator.core.messagebroker.proposalack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.doc.generator.core.api.dto.AgreementDTO;
import org.javaguru.doc.generator.core.api.dto.ProposalGenerationAcknowledgment;
import org.javaguru.doc.generator.core.messagebroker.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalGenerationAckQueueSenderImpl implements ProposalGenerationAckQueueSender {

    private static final Logger logger = LoggerFactory.getLogger(ProposalGenerationAckQueueSenderImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(AgreementDTO agreement, String proposalFilePath) {
        ProposalGenerationAcknowledgment ackMessage = new ProposalGenerationAcknowledgment();
        ackMessage.setAgreementUuid(agreement.getUuid());
        ackMessage.setProposalFilePath(proposalFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(ackMessage);
            logger.info("PROPOSAL GENERATION ACK message content: " + json);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_PROPOSAL_GENERATION_ACK, json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert proposal generation ack to JSON", e);
        } catch (AmqpException e) {
            logger.error("Error to sent proposal generation ack message", e);
        }
    }
}
