package lv.javaguru.black_list.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.black_list.core.api.dto.ValidationErrorDTO;

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

