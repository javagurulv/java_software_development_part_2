package org.javaguru.travel.insurance.core.validations;

import java.util.List;
import java.util.Optional;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;


public interface TravelAgreementFieldValidation {

    Optional<ValidationErrorDTO> validate(AgreementDTO agreement);

    List<ValidationErrorDTO> validateList(AgreementDTO agreement);

}
