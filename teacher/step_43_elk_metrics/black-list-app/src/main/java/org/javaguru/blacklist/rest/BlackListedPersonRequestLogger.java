package org.javaguru.blacklist.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.blacklist.dto.BlackListedPersonCheckRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class BlackListedPersonRequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(BlackListedPersonRequestLogger.class);

    void log(BlackListedPersonCheckRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }

}
