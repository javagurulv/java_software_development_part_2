package lv.javaguru.travel.insurance.core.services.process_agreement_service.rabbit_mq_messages;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {
    static final String QUEUE_AGREEMENT = "agreement.queue";
    static final String QUEUE_AGREEMENT_XML = "agreementXML.queue";
    static final String QUEUE_PROPOSAL_GENERATION_ACK = "q.proposal-generation-ack";

    static final String QUEUE_SAVED_FILES_INFO_DLQ = "dead-letter.queue";


    @Bean
    public Queue queueAgreementCreate() {
        return new Queue(QUEUE_AGREEMENT);
    }

    @Bean
    public Queue queueSavedFilesResponse() {
        return new Queue(QUEUE_PROPOSAL_GENERATION_ACK);
    }
    @Bean
    public Queue createDLQ() {
        return new Queue(QUEUE_SAVED_FILES_INFO_DLQ);
    }

    @Bean
    public Queue queueAgreementXMLCreate() {
        return new Queue(QUEUE_AGREEMENT_XML);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}