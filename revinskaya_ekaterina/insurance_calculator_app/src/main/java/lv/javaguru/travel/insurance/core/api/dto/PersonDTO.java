package lv.javaguru.travel.insurance.core.api.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    @XmlElement(name = "personFirstName")
    private String personFirstName;

    @XmlElement(name = "personLastName")
    private String personLastName;

    @XmlElement(name = "personBirthDate")
    private Date personBirthDate;

    @XmlElement(name = "medicalRiskLimitLevel")
    private String medicalRiskLimitLevel;

    @XmlElement(name = "personalCode")
    private String personalCode;

    @XmlElementWrapper(name = "risks")
    @XmlElement(name = "risk")
    private List<RiskDTO> risks;

}
