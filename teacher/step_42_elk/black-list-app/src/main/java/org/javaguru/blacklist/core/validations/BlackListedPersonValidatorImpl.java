package org.javaguru.blacklist.core.validations;

import org.javaguru.blacklist.core.api.BlackListedPersonDTO;
import org.javaguru.blacklist.core.api.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class BlackListedPersonValidatorImpl implements BlackListedPersonValidator {

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationErrorDTO> validate(BlackListedPersonDTO personDTO) {
        List<ValidationErrorDTO> errors = new ArrayList<>();
        validatePersonFirstName(personDTO).ifPresent(errors::add);
        validatePersonLastName(personDTO).ifPresent(errors::add);
        validatePersonCode(personDTO).ifPresent(errors::add);

        return errors;
    }


    private Optional<ValidationErrorDTO> validatePersonFirstName(BlackListedPersonDTO personDTO) {
        return (personDTO.getPersonFirstName() == null || personDTO.getPersonFirstName().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> validatePersonLastName(BlackListedPersonDTO personDTO) {
        return (personDTO.getPersonLastName() == null || personDTO.getPersonLastName().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_2"))
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> validatePersonCode(BlackListedPersonDTO personDTO) {
        return (personDTO.getPersonCode() == null || personDTO.getPersonCode().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }

}
