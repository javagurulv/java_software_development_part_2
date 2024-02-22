package lv.javaguru.travel.insurance.core.services.information_to_file_saver;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entity.AgreementEntity;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class WriterToFile {

    public void writeAgreementsToXmlFile(AgreementDTO agreement, String filePath) throws JAXBException, IOException {
        File file = new File(filePath);
        file.createNewFile();
        JAXBContext jaxbContext = JAXBContext.newInstance(AgreementDTO.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(agreement, new FileWriter(filePath));
    }
}
