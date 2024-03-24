package lv.javaguru.generator.core.servises.rabbit_mq;

import lv.javaguru.generator.core.servises.conver_to_pdf.ConvertToPDFService;
import lv.javaguru.generator.core.servises.send_to_file_storage.SendFileToFileStorage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReceiveAgreementQueueListener {
    @Value("${rabbitmq.total.retry.count:3}")
    private int totalRetryCount;

    @Autowired
    private MessageLogger logger;
    @Autowired
    private SendFileToFileStorage sendFileToFileStorage;
    @Autowired
    private ConvertToPDFService convertToPDFService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.QUEUE_PROPOSAL_GENERATION)
    public void receiveMessage(Message message){
        try {
            processMessage(message);
        } catch (Exception e) {
            logger.logError(e.getMessage());
            retryOrForwardToDeadLetterQueue(message);
        }

    }

    void processMessage(Message message) throws IOException, InterruptedException {
        String messageBody = new String(message.getBody());
        logger.logReceivedAgreement(messageBody);
        String uuid = convertToPDFService.convertAgreementToPDF(messageBody);
        sendFileToFileStorage.sendToFileStorage(uuid);
    }

    private void retryOrForwardToDeadLetterQueue(Message message) {
        int currentRetryCount = (int) message.getMessageProperties().getHeaders().getOrDefault("x-retry-count", 0);
        logger.logRetryCount(currentRetryCount);
        if (currentRetryCount < totalRetryCount) {
            message.getMessageProperties().setHeader("x-retry-count", currentRetryCount + 1);
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_PROPOSAL_GENERATION, message);
        } else {
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_PROPOSAL_GENERATION_DLQ, message);
        }
    }
}
