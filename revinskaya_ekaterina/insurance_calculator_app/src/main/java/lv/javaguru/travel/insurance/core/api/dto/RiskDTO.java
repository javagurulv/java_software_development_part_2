package lv.javaguru.travel.insurance.core.api.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class RiskDTO {
    @XmlElement(name = "riskIc")
    private String riskIc;
    @XmlElement(name = "premium")
    private BigDecimal premium;

}
