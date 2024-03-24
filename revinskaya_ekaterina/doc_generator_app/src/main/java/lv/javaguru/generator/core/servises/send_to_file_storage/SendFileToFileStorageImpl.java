package lv.javaguru.generator.core.servises.send_to_file_storage;

import lv.javaguru.generator.core.servises.rabbit_mq.MessageSender;
import lv.javaguru.generator.core.servises.send_to_file_storage.dto.SaveFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Component
class SendFileToFileStorageImpl implements SendFileToFileStorage {
    @Value("${proposals.directory.path}")
    private String proposalsDirectoryPath;

    @Value("${file-storage.pdf-file-saver.url}")
    private String pdfFileSaverURL;

    @Autowired
    private SenderLogger senderLogger;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageSender messageSender;

    @Override
    public void sendToFileStorage(String uuid){
        String filePath = proposalsDirectoryPath + "/agreement-" + uuid + ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("file", new FileSystemResource(filePath));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            SaveFileResponse response = restTemplate.postForObject(pdfFileSaverURL + uuid,
                    requestEntity, SaveFileResponse.class);
            senderLogger.log("success sent to file-storage by filePath " + response.getFilePath()
                    + " for agreement with uuid " + response.getAgreementUuid());

            messageSender.sendMessage(response);

        } catch (RestClientException e) {
            senderLogger.logError(e.getMessage());
        }
    }
}
