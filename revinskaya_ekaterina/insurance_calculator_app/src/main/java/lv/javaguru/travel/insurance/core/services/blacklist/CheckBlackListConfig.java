package lv.javaguru.travel.insurance.core.services.blacklist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CheckBlackListConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
