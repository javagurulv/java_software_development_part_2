package org.javaguru.doc.generator.core.messagebroker;

import org.javaguru.doc.generator.core.api.dto.AgreementDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

@Component
class ProposalGenerator {

    @Value( "${proposals.directory.path}" )
    private String proposalsDirectoryPath;

    public void generateProposalAndStoreToFile(AgreementDTO agreementDTO) throws IOException {
        Path path = Path.of(proposalsDirectoryPath + "/" + buildFileName(agreementDTO));
        Files.write(path, Collections.singleton(agreementDTO.toString()), StandardOpenOption.CREATE);
    }

    private String buildFileName(AgreementDTO agreementDTO) {
        return "agreement-proposal-" + agreementDTO.getUuid() + ".txt";
    }

}
