package org.javaguru.doc.generator.core.messagebroker.proposal;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.javaguru.doc.generator.core.api.dto.AgreementDTO;
import org.javaguru.doc.generator.core.api.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class PDFDocumentAgreement {

    @Value("${proposals.directory.path}")
    private String proposalsDirectoryPath;

    private final static PDFont bolt = PDType1Font.HELVETICA_BOLD;
    private final static PDFont plane = PDType1Font.HELVETICA;
    private float Y;
    public final static int X = 50;

    public PDDocument getPDFAgreement(AgreementDTO agreementDTO) {
        try {
            Y = 700;
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            addWrapperTextCentered("Agreement travel insurance", 22, bolt, contentStream, page);

            updateY(-15);
            addHeaderAndWrapperText("Agreement date from: ", agreementDTO.getAgreementDateFrom().toString(), contentStream);
            addHeaderAndWrapperText("Agreement date to: ", agreementDTO.getAgreementDateTo().toString(), contentStream);
            addHeaderAndWrapperText("Country: ", agreementDTO.getCountry().toString(), contentStream);
            addHeaderAndWrapperText("Risks: ", agreementDTO.getSelectedRisks().toString(), contentStream);
            addHeaderAndWrapperTextPerson(agreementDTO, contentStream);
            addHeaderAndWrapperText("Agreement premium: ", agreementDTO.getAgreementPremium().toString(), contentStream);
            contentStream.close();
            document.save(proposalsDirectoryPath + "/" + "agreement-proposal-" + agreementDTO.getUuid() + ".pdf");
            document.close();
            return document;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void addWrapperTextCentered(String text, int size, PDFont font, PDPageContentStream contentStream, PDPage page) throws IOException {
        String[] wrappedText = text.split("\\r?\\n");
        String string;
        for (int i = 0; i < wrappedText.length; i++) {
            updateY(-15);
            contentStream.beginText();

            contentStream.setFont(PDType1Font.HELVETICA, size);
            string = wrappedText[i];
            float titleWidth = font.getStringWidth(string) / 1000 * size;
            contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, Y);
            contentStream.showText(string);
            contentStream.endText();

        }
    }

    private void addHeaderAndWrapperText(String strHeader, String text, PDPageContentStream contentStream) throws IOException {
        updateY(-15);
        contentStream.beginText();
        contentStream.newLineAtOffset(X, Y);
        contentStream.setFont(bolt, 12);
        contentStream.showText(strHeader);
        contentStream.setFont(plane, 12);
        String[] wrappedText = text.split("\\r?\\n");
        String string;
        for (int i = 0; i < wrappedText.length; i++) {
            if (i != 0) {
                updateY(-15);
                contentStream.beginText();
                contentStream.newLineAtOffset(X, Y);
            }
            string = wrappedText[i];
            contentStream.showText(string);
            contentStream.endText();
        }

    }

    private void addHeaderAndWrapperTextPerson(AgreementDTO agreementDTO, PDPageContentStream contentStream) throws IOException {

        List<PersonDTO> persons = agreementDTO.getPersons();
        for (PersonDTO person : persons) {

            addHeaderAndWrapperText("First name: ", person.getPersonFirstName().toString(), contentStream);
            addHeaderAndWrapperText("Last name: ", person.getPersonLastName().toString(), contentStream);
            addHeaderAndWrapperText("Person code: ", person.getPersonCode().toString(), contentStream);
            addHeaderAndWrapperText("Birthdate: ", person.getPersonBirthDate().toString(), contentStream);
            addHeaderAndWrapperText("Medical risk limit level: ", person.getMedicalRiskLimitLevel().toString(), contentStream);

        }
    }


    private void updateY(int i) {
        Y += i;
    }
}
