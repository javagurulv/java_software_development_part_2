package org.javaguru.travel.insurance.core.messagebroker.proposalack;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalGenerationAck {

    private String agreementUuid;
    private String proposalFilePath;

}
