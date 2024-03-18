package lv.javaguru.generator.core.servises.conver_to_pdf;

import java.io.IOException;

public interface ConvertToPDFService {
    String convertAgreementToPDF(String agreementJSONString) throws IOException;
}
