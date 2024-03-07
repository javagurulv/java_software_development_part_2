package org.javaguru.doc.generator.core.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PersonDTO {

    private String personFirstName;

    private String personLastName;

    private String personCode;

    private Date personBirthDate;

    private String medicalRiskLimitLevel;

    private BigDecimal travelCost;

    private List<RiskDTO> risks;

    @Override
    public String toString() {
        return          personFirstName + " " + personLastName + '\n' +
                        "person code: " + personCode + '\n' +
                        personBirthDate +'\n' +
                        medicalRiskLimitLevel + '\n' +
                        travelCost +'\n' +
                        risks + '\n';
    }
}
