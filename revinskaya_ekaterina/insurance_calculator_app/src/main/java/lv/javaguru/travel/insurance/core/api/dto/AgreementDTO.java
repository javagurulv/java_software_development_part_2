package lv.javaguru.travel.insurance.core.api.dto;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class AgreementDTO {
    @XmlElement(name = "uuid")
    private String uuid;

    @XmlElement(name = "travelCost")
    private BigDecimal travelCost;

    @XmlElement(name = "agreementDateFrom")
    private Date agreementDateFrom;

    @XmlElement(name = "agreementDateTo")
    private Date agreementDateTo;

    @XmlElement(name = "country")
    private String country;

    @XmlElementWrapper(name = "selectedRisks")
    @XmlElement(name = "selectedRisk")
    private List<String> selectedRisks;

    @XmlElementWrapper(name = "persons")
    @XmlElement(name = "person")
    private List<PersonDTO> persons;

    @XmlElement(name = "agreementPremium")
    private BigDecimal agreementPremium;

}
