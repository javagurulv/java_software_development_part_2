package lv.javaguru.generator.core.servises.sender;

import java.io.IOException;

public interface SendFileToFileStorage {
    void sendToFileStorage(String uuid) throws IOException, InterruptedException;
}
