package org.javaguru.travel.insurance.core.validations.person;

import java.util.Date;
import java.util.Optional;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
class PersonBirthDateInThePastValidation extends TravelPersonFieldValidationImpl {

    @Autowired private DateTimeUtil dateTimeUtil;
    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        Date personBirthDate = person.getPersonBirthDate();
        Date currentDateTime = dateTimeUtil.getCurrentDateTime();
        return (personBirthDate != null && personBirthDate.after(currentDateTime))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }

}
