package lv.javaguru.generator.core.servises.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SenderLogger {
    private static final Logger logger = LoggerFactory.getLogger(SenderLogger.class);

    public void log(int status) {
        logger.info("status:\n" + status);
    }
}
