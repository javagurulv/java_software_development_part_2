package org.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.TCTravelCostCoefficient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TCTravelCostCoefficientCalculator {

    @Autowired private org.javaguru.travel.insurance.core.repositories.TCTravelCostCoefficientRepository TCTravelCostCoefficientRepository;

    BigDecimal calculate(PersonDTO person) {
        return TCTravelCostCoefficientRepository.findCoefficient(person.getTravelCost())
                .map(TCTravelCostCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Travel Cost coefficient not found for travel cost = " + person.getTravelCost()));
    }

}
