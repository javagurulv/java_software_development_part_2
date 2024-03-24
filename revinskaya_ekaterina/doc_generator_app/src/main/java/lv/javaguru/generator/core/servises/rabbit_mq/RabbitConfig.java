package lv.javaguru.generator.core.servises.rabbit_mq;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String QUEUE_PROPOSAL_GENERATION = "agreement.queue";

    public static final String QUEUE_PROPOSAL_GENERATION_ACK = "q.proposal-generation-ack";
    public static final String QUEUE_PROPOSAL_GENERATION_DLQ = "q.dead-letter-queue";

    @Bean
    public Queue createProposalPdfGenerationQueue() {
        return new Queue(QUEUE_PROPOSAL_GENERATION);
    }

    @Bean
    public Queue proposalPdfGenerationDeadLetterQueue() {
        return new Queue(QUEUE_PROPOSAL_GENERATION_DLQ);
    }

    @Bean
    public Queue createProposalGenerationAckQueue() {
        return new Queue(QUEUE_PROPOSAL_GENERATION_ACK);
    }

}