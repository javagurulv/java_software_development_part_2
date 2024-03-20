package lv.javaguru.black_list.core.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.black_list.core.api.dto.PersonDTO;
import lv.javaguru.black_list.core.api.dto.ValidationErrorDTO;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class CoreResult{
    private List<ValidationErrorDTO> errors;

    private PersonDTO personDTO;
    private boolean personPresentInBlackList;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    public CoreResult(List<ValidationErrorDTO> errors) {
        this.errors = errors;
    }
    public CoreResult(PersonDTO personDTO, boolean personPresentInBlackList) {
        this.personDTO = personDTO;
        this.personPresentInBlackList = personPresentInBlackList;
    }
}
