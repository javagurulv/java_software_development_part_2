package lv.javaguru.black_list.core.validation;

import lv.javaguru.black_list.core.api.dto.PersonDTO;
import lv.javaguru.black_list.core.api.dto.ValidationErrorDTO;
import lv.javaguru.black_list.core.validation.validators.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class RequestValidationImpl implements RequestValidation {
    @Autowired
    List<RequestValidator> validators;

    @Override
    public List<ValidationErrorDTO> validate(PersonDTO personDTO){
        return validators.stream()
                .map(validator -> validator.validate(personDTO))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
