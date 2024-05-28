package org.javaguru.doc.generator.core.messagebroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProposalGenerationQueueListener {

    private static final Logger logger = LoggerFactory.getLogger(ProposalGenerationQueueListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PROPOSAL_GENERATION)
    public void receiveMessage(String message) throws IOException {
        logger.info(message);
    }

}
