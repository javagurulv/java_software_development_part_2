package org.javaguru.blacklist.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.blacklist.dto.BlackListedPersonCheckRequest;
import org.javaguru.blacklist.dto.BlackListedPersonCheckResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class BlackListedPersonResponseLogger {

    private static final Logger logger = LoggerFactory.getLogger(BlackListedPersonResponseLogger.class);

    void log(BlackListedPersonCheckResponse request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("RESPONSE: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }

}
