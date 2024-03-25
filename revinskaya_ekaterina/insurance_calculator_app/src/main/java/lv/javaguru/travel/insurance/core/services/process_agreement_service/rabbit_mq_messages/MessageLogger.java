package lv.javaguru.travel.insurance.core.services.process_agreement_service.rabbit_mq_messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageLogger {
    private static final Logger logger = LoggerFactory.getLogger(MessageLogger.class);

    public void logReceivedFileInfo(String agreement) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(agreement);
            logger.info("SAVED FILE INFO:\n" + jsonString);
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
        }

    }
    public void logRetryCount(int currentRetryCount){
        logger.info("CURRENT RETRY COUNT IS: "+currentRetryCount);
    }
    public void logError(String exception){
        logger.error("Get error by trying to process message: " + exception);
    }

}
