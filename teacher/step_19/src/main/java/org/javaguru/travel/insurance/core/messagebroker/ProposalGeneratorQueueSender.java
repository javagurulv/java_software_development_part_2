package org.javaguru.travel.insurance.core.messagebroker;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;

public interface ProposalGeneratorQueueSender {

    void send(AgreementDTO agreement);

}
