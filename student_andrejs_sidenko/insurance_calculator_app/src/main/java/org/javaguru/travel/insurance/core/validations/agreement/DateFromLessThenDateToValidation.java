package org.javaguru.travel.insurance.core.validations.agreement;

import java.util.Date;
import java.util.Optional;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
class DateFromLessThenDateToValidation extends TravelAgreementFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        Date dateFrom = agreement.getAgreementDateFrom();
        Date dateTo = agreement.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }

}
