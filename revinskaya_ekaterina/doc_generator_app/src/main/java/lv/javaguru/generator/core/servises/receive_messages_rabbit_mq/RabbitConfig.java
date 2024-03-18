package lv.javaguru.generator.core.servises.receive_messages_rabbit_mq;

import lv.javaguru.generator.core.servises.conver_to_pdf.ConvertToPDFService;
import lv.javaguru.generator.core.servises.sender.SendFileToFileStorage;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableRabbit
public class RabbitConfig {
    @Autowired
    private ReceiverLogger logger;
    @Autowired
    private SendFileToFileStorage sendFileToFileStorage;
    @Autowired
    private ConvertToPDFService convertToPDFService;

    @RabbitListener(queues = "agreement.queue")
    public void receiveMessage(String message) throws IOException, InterruptedException {
        logger.log(message);
        String uuid = convertToPDFService.convertAgreementToPDF(message);
        sendFileToFileStorage.sendToFileStorage(uuid);
    }
}