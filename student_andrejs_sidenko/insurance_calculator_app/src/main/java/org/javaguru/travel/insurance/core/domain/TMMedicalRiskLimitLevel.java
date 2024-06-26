package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "travel_medical_risk_limit_level")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TMMedicalRiskLimitLevel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medical_risk_limit_level_ic", nullable = false)
    private String medicalRiskLimitLevelIc;

    @Column(name = "coefficient", precision = 10, scale = 2, nullable = false)
    private BigDecimal coefficient;

}
