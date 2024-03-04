package org.javaguru.travel.insurance.core.api.command;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelExportAgreementToXmlCoreResult {

    private List<ValidationErrorDTO> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
