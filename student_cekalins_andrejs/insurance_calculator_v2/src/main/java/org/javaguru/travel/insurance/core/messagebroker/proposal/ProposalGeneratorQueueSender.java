package org.javaguru.travel.insurance.core.messagebroker.proposal;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;

public interface ProposalGeneratorQueueSender {

    void send(AgreementDTO agreement);

}
