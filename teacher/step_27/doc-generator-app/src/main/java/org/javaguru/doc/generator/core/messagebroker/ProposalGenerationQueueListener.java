package org.javaguru.doc.generator.core.messagebroker;

import org.javaguru.doc.generator.core.api.dto.AgreementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProposalGenerationQueueListener {

    private static final Logger logger = LoggerFactory.getLogger(ProposalGenerationQueueListener.class);

    @Autowired private JsonStringToAgreementDtoConverter agreementDtoConverter;
    @Autowired private ProposalGenerator proposalGenerator;


    @RabbitListener(queues = RabbitMQConfig.QUEUE_PROPOSAL_GENERATION)
    public void receiveMessage(String message) throws IOException {
        try {
            logger.info(message);
            AgreementDTO agreementDTO = agreementDtoConverter.convert(message);
            proposalGenerator.generateProposalAndStoreToFile(agreementDTO);
        } catch (Exception e) {
            logger.error("FAIL to process message: ", e);
        }
    }

}
