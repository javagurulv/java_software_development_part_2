package lv.javaguru.generator.core.servises.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
class SendFileToFileStorageImpl implements SendFileToFileStorage{
    @Value("${proposals.directory.path}")
    private String proposalsDirectoryPath;

    @Autowired
    private SenderLogger senderLogger;
    @Override
    public void sendToFileStorage(String uuid) throws IOException, InterruptedException {
        String filePath = proposalsDirectoryPath + "/agreement-" + uuid + ".pdf";

        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://file-storage-container:8000/pdf-file-saver/" + uuid))
                .header("Content-Type", "multipart/form-data; boundary=j43g2j464jl4tl4ly445b")
                .POST(HttpRequest.BodyPublishers.ofByteArray(fileBytes))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        senderLogger.log(response.statusCode());
    }
}
