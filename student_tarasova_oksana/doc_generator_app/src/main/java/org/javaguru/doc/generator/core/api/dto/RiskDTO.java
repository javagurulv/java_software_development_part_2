package org.javaguru.doc.generator.core.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class RiskDTO {

    private String riskIc;

    private BigDecimal premium;

}
