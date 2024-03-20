package lv.javaguru.black_list.core.validation.validators;

import lv.javaguru.black_list.core.api.dto.PersonDTO;
import lv.javaguru.black_list.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

public interface RequestValidator {
    Optional<ValidationErrorDTO> validate(PersonDTO personDTO);
}
