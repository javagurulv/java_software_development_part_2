package lv.javaguru.generator.core.servises.rabbit_mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.generator.core.servises.send_to_file_storage.dto.SaveFileResponse;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageLogger logger;

    public void sendMessage(SaveFileResponse response) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_PROPOSAL_GENERATION_ACK,
                    objectMapper.writeValueAsString(response));
        }catch(JsonProcessingException | AmqpException e){
            logger.logError(e.getMessage());
        }
    }

}


