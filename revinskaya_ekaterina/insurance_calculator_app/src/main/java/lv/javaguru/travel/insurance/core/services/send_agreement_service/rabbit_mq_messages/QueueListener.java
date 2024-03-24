package lv.javaguru.travel.insurance.core.services.send_agreement_service.rabbit_mq_messages;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class QueueListener {
    @Value("${rabbitmq.total.retry.count:3}")
    private int totalRetryCount;

    @Autowired
    private MessageLogger logger;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.QUEUE_PROPOSAL_GENERATION_ACK)
    public void receiveMessage(Message message){
        try {
            processMessage(message);
        } catch (Exception e) {
            logger.logError(e.getMessage());
            retryOrForwardToDeadLetterQueue(message);
        }

    }

    void processMessage(Message message){
        String messageBody = new String(message.getBody());
        logger.logReceivedFileInfo(messageBody);
    }

    private void retryOrForwardToDeadLetterQueue(Message message) {
        int currentRetryCount = (int) message.getMessageProperties().getHeaders().getOrDefault("x-retry-count", 0);
        logger.logRetryCount(currentRetryCount);
        if (currentRetryCount < totalRetryCount) {
            message.getMessageProperties().setHeader("x-retry-count", currentRetryCount + 1);
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_PROPOSAL_GENERATION_ACK, message);
        } else {
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_SAVED_FILES_INFO_DLQ, message);
        }
    }
}


