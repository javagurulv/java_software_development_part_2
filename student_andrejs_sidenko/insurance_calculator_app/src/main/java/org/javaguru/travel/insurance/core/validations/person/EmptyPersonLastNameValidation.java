package org.javaguru.travel.insurance.core.validations.person;

import java.util.Optional;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
class EmptyPersonLastNameValidation extends TravelPersonFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (person.getPersonLastName() == null || person.getPersonLastName().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

}
