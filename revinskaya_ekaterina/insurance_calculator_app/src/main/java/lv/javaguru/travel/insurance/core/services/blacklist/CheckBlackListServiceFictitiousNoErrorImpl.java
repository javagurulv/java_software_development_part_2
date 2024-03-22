package lv.javaguru.travel.insurance.core.services.blacklist;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.blacklist.dto.CheckPersonInBlackListRequest;
import lv.javaguru.travel.insurance.core.services.blacklist.dto.CheckPersonInBlackListResponse;
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
@Profile({"case1", "case2", "case3", "case4"})
class CheckBlackListServiceFictitiousNoErrorImpl implements CheckBlackListService {
    @Override
    public List<ValidationErrorDTO> checkPersons(List<PersonDTO> persons) {
        return List.of();
    }

}
