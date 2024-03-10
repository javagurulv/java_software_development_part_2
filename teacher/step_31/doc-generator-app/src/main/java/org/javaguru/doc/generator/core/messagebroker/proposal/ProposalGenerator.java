package org.javaguru.doc.generator.core.messagebroker.proposal;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.javaguru.doc.generator.core.api.dto.AgreementDTO;
import org.javaguru.doc.generator.core.api.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class ProposalGenerator {

    private static final Logger logger = LoggerFactory.getLogger(ProposalGenerator.class);

    @Value( "${proposals.directory.path}" )
    private String proposalsDirectoryPath;

    private final static PDFont bolt = PDType1Font.HELVETICA_BOLD;
    private final static PDFont plane = PDType1Font.HELVETICA;


    public String generateProposalAndStoreToFile(AgreementDTO agreementDTO) throws IOException {
        logger.info("Start to generate PDF for proposal: " + agreementDTO.getUuid());

        try {
            OffsetContext offsetContext = new OffsetContext(50, 700);
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            addWrapperTextCentered("Agreement travel insurance", 22, bolt, contentStream, page, offsetContext);

            updateY(offsetContext, -15);
            addHeaderAndWrapperText("agreement date from: ", agreementDTO.getAgreementDateFrom().toString(), contentStream, offsetContext);
            addHeaderAndWrapperText("agreement date to: ", agreementDTO.getAgreementDateTo().toString(), contentStream, offsetContext);
            addHeaderAndWrapperText("country: ", agreementDTO.getCountry(), contentStream, offsetContext);

            for (String selectedRisk : agreementDTO.getSelectedRisks()) {
                addHeaderAndWrapperText("Selected risk: ", selectedRisk, contentStream,  offsetContext);
            }

            addHeaderAndWrapperText("Persons: ", "", contentStream,  offsetContext);
            for (PersonDTO person : agreementDTO.getPersons()) {
                addHeaderAndWrapperText("Person first name: ", person.getPersonFirstName(), contentStream,  offsetContext);
                addHeaderAndWrapperText("Person last name: ", person.getPersonLastName(), contentStream,  offsetContext);
                addHeaderAndWrapperText("Person code : ", person.getPersonCode(), contentStream,  offsetContext);
                addHeaderAndWrapperText("Person birth date : ", person.getPersonBirthDate().toString(), contentStream,  offsetContext);

                if (person.getMedicalRiskLimitLevel() != null) {
                    addHeaderAndWrapperText("Person medical risk limit level : ", person.getMedicalRiskLimitLevel(), contentStream, offsetContext);
                }

                if (person.getTravelCost() != null) {
                    addHeaderAndWrapperText("Person travel cost : ", person.getTravelCost().toString(), contentStream, offsetContext);
                }
            }

            addHeaderAndWrapperText("Agreement premium: ", agreementDTO.getAgreementPremium().toString(), contentStream, offsetContext);
            contentStream.close();
            String filePath = proposalsDirectoryPath + "/" + buildFileName(agreementDTO);
            document.save(filePath);
            document.close();
            logger.info("Finish to generate PDF for proposal: " + agreementDTO.getUuid());
            return filePath;
        } catch (IOException e) {
            logger.error("Proposal generation error!", e);
            throw new RuntimeException(e);
        }
    }

    private String buildFileName(AgreementDTO agreementDTO) {
        return "agreement-proposal-" + agreementDTO.getUuid() + ".pdf";
    }

    private void addWrapperTextCentered(String text,
                                        int size,
                                        PDFont font,
                                        PDPageContentStream contentStream,
                                        PDPage page,
                                        OffsetContext offsetContext) throws IOException {
        String[] wrappedText = text.split("\\r?\\n");
        String string;
        for (int i = 0; i < wrappedText.length; i++) {
            updateY(offsetContext, -15);
            contentStream.beginText();

            contentStream.setFont(PDType1Font.HELVETICA, size);
            string = wrappedText[i];
            float titleWidth = font.getStringWidth(string) / 1000 * size;
            contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, offsetContext.getY());
            contentStream.showText(string);
            contentStream.endText();

        }
    }

    private void addHeaderAndWrapperText(String strHeader,
                                         String text,
                                         PDPageContentStream contentStream,
                                         OffsetContext offsetContext) throws IOException {
        updateY(offsetContext, -15);
        contentStream.beginText();
        contentStream.newLineAtOffset(offsetContext.getX(), offsetContext.Y);
        contentStream.setFont(bolt, 12);
        contentStream.showText(strHeader);
        contentStream.setFont(plane, 12);
        String[] wrappedText = text.split("\\r?\\n");
        String string;
        for (int i = 0; i < wrappedText.length; i++) {
            if (i != 0) {
                updateY(offsetContext, -15);
                contentStream.beginText();
                contentStream.newLineAtOffset(offsetContext.getX(), offsetContext.getY());
            }
            string = wrappedText[i];
            contentStream.showText(string);
            contentStream.endText();
        }

    }

    private void addHeaderAndWrapperTextPerson(String strHeader,
                                               String text,
                                               PDPageContentStream contentStream,
                                               OffsetContext offsetContext) throws IOException {
        updateY(offsetContext, -15);
        contentStream.beginText();
        contentStream.newLineAtOffset(offsetContext.getX(), offsetContext.getY());
        contentStream.setFont(bolt, 12);
        contentStream.showText(strHeader);
        contentStream.setFont(plane, 12);

        String[] wrappedText = text.split("\\r?\\n");
        String string;
        for (int i = 0; i < wrappedText.length; i++) {
            if (i != 0) {
                updateY(offsetContext, -15);
                contentStream.beginText();
                contentStream.newLineAtOffset(offsetContext.getX(), offsetContext.getY());
            }
            string = wrappedText[i];

            contentStream.showText(string);
            contentStream.endText();
        }

    }

    private void updateY(OffsetContext offsetContext, int i) {
        offsetContext.setY(offsetContext.getY() + i);
    }

    private class OffsetContext {
        private float X;
        private float Y;

        public OffsetContext(float x, float y) {
            X = x;
            Y = y;
        }

        public float getX() {
            return X;
        }

        public void setX(float x) {
            X = x;
        }

        public float getY() {
            return Y;
        }

        public void setY(float y) {
            Y = y;
        }
    }

}
