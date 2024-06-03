package org.javaguru.doc.generator.core.messagebroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

@Component
public class ProposalGenerationQueueListener {

    private static final Logger logger = LoggerFactory.getLogger(ProposalGenerationQueueListener.class);

    @Value( "${proposals.directory.path}" )
    private String proposalsDirectoryPath;


    @RabbitListener(queues = RabbitMQConfig.QUEUE_PROPOSAL_GENERATION)
    public void receiveMessage(String message) throws IOException {
        logger.info(message);

        Path path = Path.of(proposalsDirectoryPath + "/proposal.txt");

        try {
            Files.write(path, Collections.singleton(message), StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
