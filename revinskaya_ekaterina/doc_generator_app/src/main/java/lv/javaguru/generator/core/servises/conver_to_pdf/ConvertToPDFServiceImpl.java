package lv.javaguru.generator.core.servises.conver_to_pdf;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.generator.core.dto.AgreementDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConvertToPDFServiceImpl implements ConvertToPDFService {
    @Value("${proposals.directory.path}")
    private String proposalsDirectoryPath;

    private PDFont bolt = PDType1Font.HELVETICA_BOLD;
    private PDFont plane = PDType1Font.HELVETICA;
    private float y;
    public float x = 50;

    @Override
    public String convertAgreementToPDF(String agreementJSONString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AgreementDTO agreementDTO = mapper.readValue(agreementJSONString, AgreementDTO.class);
        String uuid = agreementDTO.getUuid();

        try {
            y = 700;
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            addWrapperTextCentered("Agreement travel insurance", 22, bolt, contentStream, page);

            y -= 15;
            addHeaderAndWrapperText("agreement date from: ", agreementDTO.getAgreementDateFrom().toString(), contentStream);
            y -= 15;
            addHeaderAndWrapperText("agreement date to: ", agreementDTO.getAgreementDateTo().toString(), contentStream);
            y -= 15;
            addHeaderAndWrapperText("country: ", agreementDTO.getCountry(), contentStream);
            y -= 15;
            addHeaderAndWrapperText("Risks: ", agreementDTO.getSelectedRisks().toString(), contentStream);
            y -= 15;
            addHeaderAndWrapperTextPerson("Persons: ", agreementDTO.getPersons().toString(), contentStream);
            y -= 15;
            addHeaderAndWrapperText("Agreement premium: ", agreementDTO.getAgreementPremium().toString(), contentStream);
            y -= 15;
            contentStream.close();
            document.save(proposalsDirectoryPath + "/agreement-" + uuid + ".pdf");
            // document.save(proposalsDirectoryPath + "/eement-" + uuid + ".pdf");
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return uuid;
    }

    private void addWrapperTextCentered(String text, int size, PDFont font, PDPageContentStream contentStream, PDPage page) throws IOException {
        String[] wrappedText = text.split("\\r?\\n");
        String string;
        for (int i = 0; i < wrappedText.length; i++) {
            y -= 15;
            contentStream.setFont(PDType1Font.HELVETICA, size);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            string = wrappedText[i];
            // float titleWidth = font.getStringWidth(string) / 1000 * size;
            //  contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, y);
            contentStream.showText(string);
            contentStream.endText();

        }
    }

    private void addHeaderAndWrapperText(String strHeader, String text, PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(bolt, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(strHeader);
        contentStream.endText();

        String[] wrappedText = text.split("\\r?\\n");
        String string;
        for (int i = 0; i < wrappedText.length; i++) {
            y -= 15;
            contentStream.setFont(plane, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            string = wrappedText[i];
            contentStream.showText(string);
            contentStream.endText();
        }

    }

    private void addHeaderAndWrapperTextPerson(String strHeader, String text, PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(bolt, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(strHeader);
        contentStream.endText();
        String[] wrappedText = text.split("\\r?\\n");
        String string;
        for (int i = 0; i < wrappedText.length; i++) {
            y -= 15;
            contentStream.setFont(plane, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            string = wrappedText[i];
            contentStream.showText(string);
            contentStream.endText();
        }

    }

}
