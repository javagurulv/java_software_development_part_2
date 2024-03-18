package lv.javaguru.travel.insurance.core.services.send_agreement_service.send_messages_rabbit_mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {
    private static final String EXCHANGE_NAME = "agreement.exchanger";
    private static final String QUEUE_NAME_1 = "agreement.queue";
    private static final String QUEUE_NAME_2 = "agreementXML.queue";
    private static final String ROUTING_KEY = "agreement";
    private static final String ROUTING_KEY_2 = "agreementXML";


    @Bean
    public Exchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_NAME_1, true, false, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_NAME_2, true, false, false);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(exchange()).with(ROUTING_KEY).noargs();
    }
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(exchange()).with(ROUTING_KEY_2).noargs();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}