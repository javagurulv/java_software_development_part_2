package lv.javaguru.generator.core.dto;

import lombok.*;

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

    private Date personBirthDate;

    private String medicalRiskLimitLevel;

    private String personalCode;

    private List<RiskDTO> risks;
    @Override
    public String toString() {
        return          personFirstName + " " + personLastName + '\n' +
                "person code: " + personalCode + '\n' +
                personBirthDate.toString() +'\n' +
                medicalRiskLimitLevel + '\n' +
                risks + '\n';
    }

}
