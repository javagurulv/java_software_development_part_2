package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.blacklist.CheckBlackListService;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorForTotalAgreementPremium;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorRiskPremiumsForAllPersons;
import lv.javaguru.travel.insurance.core.services.entities_to_db_savers.PolicySaver;
import lv.javaguru.travel.insurance.core.services.process_agreement_service.SendAgreementService;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumServiceImpl.class);

    @Autowired
    private TravelAgreementValidator agreementValidator;
    @Autowired
    private CalculatorForTotalAgreementPremium calculatorForTotalAgreementPremium;
    @Autowired
    private CalculatorRiskPremiumsForAllPersons calculatorRiskPremiumsForAllPersons;
    @Autowired
    private PolicySaver policySaver;
    @Autowired
    private SendAgreementService sendAgreementService;

    @Autowired
    private CheckBlackListService checkBlackListService;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command)
            throws IOException, InterruptedException {

        List<ValidationErrorDTO> validationErrors = agreementValidator.validate(command.getAgreement());
        if (!validationErrors.isEmpty()) {
            return buildValidationErrorResponse(validationErrors);
        }
        try{
            List<ValidationErrorDTO> personInBlackListErrors = checkPersonsPresentInBlackList(command.getAgreement().getPersons());
            return personInBlackListErrors.isEmpty()
                    ? buildSuccessResponse(command.getAgreement()) :
                    buildPersonInBlackListErrorResponse(personInBlackListErrors);
        }
        catch(Throwable e){
            logger.error("BlackList error!!!", e);
            return buildNoConnectionToBlackListErrorResponse();
        }
    }

    private List<ValidationErrorDTO> checkPersonsPresentInBlackList(List<PersonDTO> persons) throws IOException, InterruptedException {
        return checkBlackListService.checkPersons(persons);
    }

    private TravelCalculatePremiumCoreResult buildPersonInBlackListErrorResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }
    private TravelCalculatePremiumCoreResult buildNoConnectionToBlackListErrorResponse() {
        return new TravelCalculatePremiumCoreResult(List.of(validationErrorFactory.buildError("ERROR_CODE_25")));
    }

    private TravelCalculatePremiumCoreResult buildValidationErrorResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private TravelCalculatePremiumCoreResult buildSuccessResponse(AgreementDTO agreementDTO) {
        calculatorRiskPremiumsForAllPersons.calculate(agreementDTO);
        BigDecimal totalAgreementPremium = calculatorForTotalAgreementPremium.calculate(agreementDTO);
        agreementDTO.setAgreementPremium(totalAgreementPremium);
        policySaver.savePolicy(agreementDTO);
        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreementDTO);
        sendAgreementService.sendAgreement(agreementDTO);
        return coreResult;
    }


}