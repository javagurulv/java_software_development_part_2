package org.javaguru.travel.insurance.core.messagebroker.proposalack;

import org.javaguru.travel.insurance.core.domain.entities.AgreementProposalAckEntity;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementProposalAckEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
class ProposalGenerationAckService {

    private static final Logger logger = LoggerFactory.getLogger(ProposalGenerationAckService.class);

    @Autowired private AgreementProposalAckEntityRepository proposalAckEntityRepository;

    public void process(ProposalGenerationAck proposalGenerationAck) {
        logger.info("Start to process proposal ack: " + proposalGenerationAck.getAgreementUuid());

        AgreementProposalAckEntity ack = new AgreementProposalAckEntity();
        ack.setAgreementUuid(proposalGenerationAck.getAgreementUuid());
        ack.setAlreadyGenerated(true);
        ack.setProposalFilePath(proposalGenerationAck.getProposalFilePath());

        proposalAckEntityRepository.save(ack);

        logger.info("Finish to process proposal ack: " + proposalGenerationAck.getAgreementUuid());
    }

}
