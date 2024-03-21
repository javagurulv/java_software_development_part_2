package lv.javaguru.black_list.core.servises;

import lv.javaguru.black_list.core.api.command.CoreCommand;
import lv.javaguru.black_list.core.api.command.CoreResult;
import lv.javaguru.black_list.core.api.dto.PersonDTO;
import lv.javaguru.black_list.core.api.dto.ValidationErrorDTO;
import lv.javaguru.black_list.core.repositories.PersonRepository;
import lv.javaguru.black_list.core.validation.RequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class CheckPersonServiceImpl implements CheckPersonService {
    @Autowired
    private RequestValidation requestValidation;
    @Autowired
    private PersonRepository repository;

    @Override
    public CoreResult checkPerson(CoreCommand coreCommand) {
        PersonDTO personDTO = coreCommand.getPersonDTO();
        List<ValidationErrorDTO> errors = requestValidation.validate(personDTO);
        return errors.isEmpty() ? buildSuccessResponse(personDTO) : buildErrorResponse(errors);
    }

    private CoreResult buildErrorResponse(List<ValidationErrorDTO> errors) {
        return new CoreResult(errors);
    }

    private CoreResult buildSuccessResponse(PersonDTO personDTO) {
        boolean presentInList = repository.existByPersonalCode(personDTO.getPersonalCode());
        personDTO.setBlackListed(presentInList);
        return new CoreResult(personDTO);
    }
}
