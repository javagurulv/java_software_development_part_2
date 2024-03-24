package lv.javaguru.generator.core.servises.send_to_file_storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FileStorageConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
