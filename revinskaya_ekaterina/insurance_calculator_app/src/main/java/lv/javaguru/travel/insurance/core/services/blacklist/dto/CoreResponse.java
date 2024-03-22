package lv.javaguru.travel.insurance.core.services.blacklist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoreResponse {

    private List<ValidationErrorDTO> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}

