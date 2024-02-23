package lv.javaguru.travel.insurance.core.services.information_to_file_saver;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.repositories.entity.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class Job implements Runnable {
    private Long agreementId;
    private String filepath;
    private AgreementInformationBuilder agreementInformationBuilder;
    private AgreementRepository agreementRepository;
    private WriterToFile writerToFile;

    public Job(Long agreementId, String filepath,
               AgreementInformationBuilder agreementInformationBuilder,
               AgreementRepository agreementRepository, WriterToFile writerToFile) {
        this.agreementId = agreementId;
        this.filepath = filepath;
        this.agreementRepository = agreementRepository;
        this.agreementInformationBuilder = agreementInformationBuilder;
        this.writerToFile = writerToFile;
    }

    @Override
    public void run() {
        AgreementDTO agreementDTO = agreementInformationBuilder.getAgreementDTOById(agreementId);
        try {
            writerToFile.writeAgreementsToXmlFile(agreementDTO, filepath + agreementId + ".xml");
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
        agreementRepository.updateColumnValueById(agreementId);
    }

}
