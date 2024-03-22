package lv.javaguru.travel.insurance.core.services.blacklist;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.io.IOException;
import java.util.List;

public interface CheckBlackListService {
    List<ValidationErrorDTO> checkPersons(List<PersonDTO> persons);
}
