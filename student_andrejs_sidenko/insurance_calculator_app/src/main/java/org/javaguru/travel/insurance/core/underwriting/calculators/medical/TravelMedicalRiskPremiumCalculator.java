package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Autowired private TMDayCountCalculator dayCountCalculator;
    @Autowired private TMCountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Autowired private TMAgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired private TMRiskLimitLevelCalculator riskLimitLevelCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        var daysCount = dayCountCalculator.calculate(agreement);
        var countryDefaultRate = countryDefaultDayRateCalculator.calculate(agreement);
        var ageCoefficient = ageCoefficientCalculator.calculate(person);
        var riskLimitLevel = riskLimitLevelCalculator.calculate(person);
        return countryDefaultRate
                .multiply(daysCount)
                .multiply(ageCoefficient)
                .multiply(riskLimitLevel)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}
