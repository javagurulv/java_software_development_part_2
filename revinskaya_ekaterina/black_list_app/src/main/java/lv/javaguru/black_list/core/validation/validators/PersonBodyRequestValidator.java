package lv.javaguru.black_list.core.validation.validators;

import lv.javaguru.black_list.core.api.dto.PersonDTO;
import lv.javaguru.black_list.core.api.dto.ValidationErrorDTO;
import lv.javaguru.black_list.core.validation.util.ValidationErrorConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonBodyRequestValidator implements RequestValidator{
    @Autowired
    ValidationErrorConstructor errorConstructor;
    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO personDTO){
        return personDTO == null ? Optional.of(errorConstructor.constructError("ERROR_CODE_4"))
                : Optional.empty();
    }
}
