package org.javaguru.travel.insurance.core.underwriting;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;


@Getter
@AllArgsConstructor
public class TravelPremiumCalculationResult {

    private BigDecimal totalPremium;

    private List<RiskDTO> risks;

}
