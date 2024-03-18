package org.javaguru.travel.insurance.rest.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumRequestLoggerV2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerV2.class);

    void log(TravelCalculatePremiumRequestV2 request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            LOGGER.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error to convert request to JSON", e);
        }
    }

}