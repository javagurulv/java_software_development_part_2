package org.javaguru.blacklist.core.validations;

import org.javaguru.blacklist.core.api.BlackListedPersonDTO;
import org.javaguru.blacklist.core.api.ValidationErrorDTO;

import java.util.List;

public interface BlackListedPersonValidator {

    List<ValidationErrorDTO> validate(BlackListedPersonDTO personDTO);

}
