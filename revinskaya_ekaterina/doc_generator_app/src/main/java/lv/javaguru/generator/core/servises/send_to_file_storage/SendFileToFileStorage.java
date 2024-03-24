package lv.javaguru.generator.core.servises.send_to_file_storage;

import java.io.IOException;

public interface SendFileToFileStorage {
    void sendToFileStorage(String uuid) throws IOException, InterruptedException;
}
