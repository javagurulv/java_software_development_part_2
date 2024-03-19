package lv.javaguru.generator.core.servises.receive_messages_rabbit_mq;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String QUEUE_PROPOSAL_GENERATION = "agreement.queue";
    public static final String QUEUE_PROPOSAL_GENERATION_DLQ = "q.dead-letter-queue";

    @Bean
    public Queue createProposalPdfGenerationQueue() {
        return new Queue(QUEUE_PROPOSAL_GENERATION);
    }

    @Bean
    public Queue proposalPdfGenerationDeadLetterQueue() {
        return new Queue(QUEUE_PROPOSAL_GENERATION_DLQ);
    }

}