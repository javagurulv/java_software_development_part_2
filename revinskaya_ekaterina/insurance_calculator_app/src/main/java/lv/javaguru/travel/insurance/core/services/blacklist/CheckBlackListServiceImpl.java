package lv.javaguru.travel.insurance.core.services.blacklist;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.blacklist.dto.CheckPersonInBlackListResponse;
import lv.javaguru.travel.insurance.core.services.blacklist.dto.CheckPersonInBlackListRequest;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile({"mysql-local", "mysql-container", "h2"})
class CheckBlackListServiceImpl implements CheckBlackListService {
    @Value("${blacklist.check.url}")
    private String blacklistCheckUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public List<ValidationErrorDTO> checkPersons(List<PersonDTO> persons){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return persons.stream()
                .map(person-> checkPerson(person, headers))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
    private Optional<ValidationErrorDTO> checkPerson(PersonDTO person, HttpHeaders headers) {
        CheckPersonInBlackListRequest request = new CheckPersonInBlackListRequest(
                person.getPersonFirstName(), person.getPersonLastName(), person.getPersonalCode());

        HttpEntity<CheckPersonInBlackListRequest> requestEntity = new HttpEntity<>(request, headers);

        CheckPersonInBlackListResponse response = restTemplate.postForObject(blacklistCheckUrl,
                requestEntity, CheckPersonInBlackListResponse.class);

        return response.getBlackListed()
                ? Optional.of(buildErrorPresentInBlackList(person.getPersonalCode()))
                : Optional.empty();
    }
    private ValidationErrorDTO buildErrorPresentInBlackList(String personalCode){
        return validationErrorFactory
                .buildError("ERROR_CODE_24",
                        List.of(new Placeholder("PERSONAL_CODE", personalCode)));
    }
}
