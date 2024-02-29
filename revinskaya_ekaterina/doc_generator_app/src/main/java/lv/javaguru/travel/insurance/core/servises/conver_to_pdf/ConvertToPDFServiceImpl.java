package lv.javaguru.travel.insurance.core.servises.conver_to_pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConvertToPDFServiceImpl implements ConvertToPDFService{
    @Override
    public void convertAgreementToPDF(String agreementJSONString) throws IOException {

        String[] strings = agreementJSONString.split(System.lineSeparator());

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.beginText();
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 725);
        for (String string : strings) {
            contentStream.showText(string);
            contentStream.newLine();
        }
        contentStream.endText();
        contentStream.close();

        document.save("agreement.pdf");
        document.close();
    }
}
