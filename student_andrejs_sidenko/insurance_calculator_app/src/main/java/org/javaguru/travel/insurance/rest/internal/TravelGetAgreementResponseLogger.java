package org.javaguru.travel.insurance.rest.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.internal.TravelGetAgreementResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelGetAgreementResponseLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelGetAgreementResponseLogger.class);

    void log(TravelGetAgreementResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(response);
            LOGGER.info("RESPONSE: " + json);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error to convert response to JSON", e);
        }
    }

}
