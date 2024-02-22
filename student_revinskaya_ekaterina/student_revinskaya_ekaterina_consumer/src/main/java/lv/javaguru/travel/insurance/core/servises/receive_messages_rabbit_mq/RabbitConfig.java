package lv.javaguru.travel.insurance.core.servises.receive_messages_rabbit_mq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {
    @Autowired
    private ReceiverLogger logger;

    @RabbitListener(queues = "agreement.queue")
    public void receiveMessage(String message) {
        logger.log(message);

    }
}