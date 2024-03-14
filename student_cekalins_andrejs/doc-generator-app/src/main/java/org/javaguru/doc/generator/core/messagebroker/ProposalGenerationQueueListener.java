package org.javaguru.doc.generator.core.messagebroker;

import org.javaguru.doc.generator.core.api.dto.AgreementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class ProposalGenerationQueueListener {

    private static final Logger logger = LoggerFactory.getLogger(ProposalGenerationQueueListener.class);

    @Value("${rabbitmq.total.retry.count:3}")
    private Integer totalRetryCount;

    @Autowired
    private JsonStringToAgreementDtoConverter agreementDtoConverter;
    @Autowired private ProposalGenerator proposalGenerator;

    @Autowired private RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = RabbitMQConfig.QUEUE_PROPOSAL_GENERATION)
    public void receiveMessage(final Message message) throws Exception {
        try {
            processMessage(message);
        } catch (Exception e) {
            logger.error("FAIL to process message: ", e);
            retryOrForwardToDeadLetterQueue(message);
        }
    }
    private void retryOrForwardToDeadLetterQueue(org.springframework.amqp.core.Message message) {
        Integer retryCount = message.getMessageProperties().getHeader("x-retry-count");
        logger.info("MESSAGE DELIVERY TAG "
                + message.getMessageProperties().getDeliveryTag()
                + " RETRY COUNT = " + retryCount);
        if (retryCount == null) {
            retryCount = 0;
        }
        retryCount++;
        if (retryCount <= totalRetryCount) {
            // Update retry count and republish for retry
            message.getMessageProperties().setHeader("x-retry-count", retryCount);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_PROPOSAL_GENERATION, message);
        } else {
            // Forward to DLQ
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_PROPOSAL_GENERATION_DLQ, message);
        }
    }

    private void processMessage(org.springframework.amqp.core.Message message) throws Exception {
        String messageBody = new String(message.getBody());
        logger.info(messageBody);
        AgreementDTO agreementDTO = agreementDtoConverter.convert(messageBody);
        proposalGenerator.generateProposalAndStoreToFile(agreementDTO);
    }

}
