package org.javaguru.travel.insurance.core.validations;

import java.util.List;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;


public interface TravelAgreementUuidValidator {

    List<ValidationErrorDTO> validate(String uuid);

}
