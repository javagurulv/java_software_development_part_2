package lv.javaguru.generator.core.servises.send_to_file_storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SenderLogger {
    private static final Logger logger = LoggerFactory.getLogger(SenderLogger.class);

    public void log(String message) {
        logger.info("DOC GENERATOR SEND FILE TO FILE STORAGE:\n" + message);
    }
    public void logError(String message) {
        logger.info("DOC GENERATOR SEND FILE TO FILE STORAGE ERROR:\n" + message);
    }

}
