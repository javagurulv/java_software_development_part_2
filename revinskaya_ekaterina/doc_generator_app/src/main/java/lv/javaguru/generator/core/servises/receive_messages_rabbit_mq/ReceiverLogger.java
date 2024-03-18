package lv.javaguru.generator.core.servises.receive_messages_rabbit_mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReceiverLogger {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverLogger.class);

    public void log(String agreement) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(agreement);
            logger.info("AGREEMENT:\n" + jsonString);
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
        }

    }
}
