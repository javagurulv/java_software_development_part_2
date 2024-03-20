package lv.javaguru.black_list.core.validation;

import lv.javaguru.black_list.core.api.dto.PersonDTO;
import lv.javaguru.black_list.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface RequestValidation {
    List<ValidationErrorDTO> validate(PersonDTO personDTO);
}
