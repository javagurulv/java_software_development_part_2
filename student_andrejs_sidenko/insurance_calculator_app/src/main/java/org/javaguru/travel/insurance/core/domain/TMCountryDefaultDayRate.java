package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "travel_medical_country_default_day_rate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TMCountryDefaultDayRate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_ic", nullable = false)
    private String countryIc;

    @Column(name = "default_day_rate", precision = 10, scale = 2, nullable = false)
    private BigDecimal defaultDayRate;

}
