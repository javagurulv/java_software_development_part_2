package org.javaguru.blacklist.core.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlackListedPersonCoreResult {

    private List<ValidationErrorDTO> errors;

    private BlackListedPersonDTO personDTO;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    public BlackListedPersonCoreResult(List<ValidationErrorDTO> errors) {
        this.errors = errors;
        this.personDTO = null;
    }

    public BlackListedPersonCoreResult(BlackListedPersonDTO personDTO) {
        this.personDTO = personDTO;
        this.errors = null;
    }
}
