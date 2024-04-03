package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.blacklist.BlackListPersonCheckService;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class PersonBlacklistedCheckValidation extends TravelPersonFieldValidationImpl {

    private static final Logger logger = LoggerFactory.getLogger(PersonBlacklistedCheckValidation.class);

    @Autowired private BlackListPersonCheckService blackListPersonCheckService;
    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (!personFirstNameIsNullOrBlank(person)
                && !personLastNameIsNullOrBlank(person)
                && !personCodeIsNullOrBlank(person))
                ? personBlackListedCheck(person)
                : Optional.empty();
    }

    private boolean personCodeIsNullOrBlank(PersonDTO person) {
        return person.getPersonCode() == null || person.getPersonCode().isBlank();
    }

    private boolean personFirstNameIsNullOrBlank(PersonDTO person) {
        return person.getPersonFirstName() == null || person.getPersonFirstName().isBlank();
    }

    private boolean personLastNameIsNullOrBlank(PersonDTO person) {
        return person.getPersonLastName() == null || person.getPersonLastName().isBlank();
    }

    private Optional<ValidationErrorDTO> personBlackListedCheck(PersonDTO person) {
        try {
            if (blackListPersonCheckService.isPersonBlacklisted(person)) {
                Placeholder placeholder = new Placeholder("PERSON_CODE", person.getPersonCode());
                return Optional.of(errorFactory.buildError("ERROR_CODE_21", List.of(placeholder)));
            }
        } catch (Throwable e) {
            logger.error("BlackList call failed!", e);
            return Optional.of(errorFactory.buildError("ERROR_CODE_22"));
        }
        return Optional.empty();
    }
}
