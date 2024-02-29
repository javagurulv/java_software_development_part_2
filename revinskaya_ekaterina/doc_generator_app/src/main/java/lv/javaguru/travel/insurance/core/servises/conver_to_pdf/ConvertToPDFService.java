package lv.javaguru.travel.insurance.core.servises.conver_to_pdf;

import java.io.IOException;

public interface ConvertToPDFService {
    void convertAgreementToPDF(String agreementJSONString) throws IOException;
}
