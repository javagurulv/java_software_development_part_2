package org.javaguru.travel.insurance.core.messagebroker.proposal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"h2", "mysql-local"})
class ProposalGeneratorQueueSenderStubImpl implements ProposalGeneratorQueueSender {

    private static final Logger logger = LoggerFactory.getLogger(ProposalGeneratorQueueSenderStubImpl.class);

    @Override
    public void send(AgreementDTO agreement) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(agreement);
            logger.info("PROPOSAL GENERATION message content: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert agreement to JSON", e);
        } catch (AmqpException e) {
            logger.error("Error to sent proposal generation message", e);
        }
    }

}
