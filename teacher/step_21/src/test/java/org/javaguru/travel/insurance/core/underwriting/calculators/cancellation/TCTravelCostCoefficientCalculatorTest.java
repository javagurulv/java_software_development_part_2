package org.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.TCTravelCostCoefficient;
import org.javaguru.travel.insurance.core.repositories.TCTravelCostCoefficientRepository;
import org.javaguru.travel.insurance.core.underwriting.calculators.cancellation.TCTravelCostCoefficientCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TCTravelCostCoefficientCalculatorTest {

    @Mock private TCTravelCostCoefficientRepository TCTravelCostCoefficientRepository;

    @InjectMocks
    private TCTravelCostCoefficientCalculator calculator;

    private PersonDTO person;

    @BeforeEach
    void setUp() {
        person = new PersonDTO();
        person.setTravelCost(BigDecimal.ONE);
    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        TCTravelCostCoefficient TCTravelCostCoefficient = mock(TCTravelCostCoefficient.class);
        when(TCTravelCostCoefficient.getCoefficient()).thenReturn(BigDecimal.TEN);
        when(TCTravelCostCoefficientRepository.findCoefficient(BigDecimal.ONE)).thenReturn(Optional.of(TCTravelCostCoefficient));

        BigDecimal result = calculator.calculate(person);

        assertEquals(BigDecimal.TEN, result);
    }

    @Test
    void shouldThrowExceptionWhenTravelCostCoefficientNotFound() {
        when(TCTravelCostCoefficientRepository.findCoefficient(BigDecimal.ONE)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(person));
        assertEquals("Travel Cost coefficient not found for travel cost = " + BigDecimal.ONE, exception.getMessage());
    }

}