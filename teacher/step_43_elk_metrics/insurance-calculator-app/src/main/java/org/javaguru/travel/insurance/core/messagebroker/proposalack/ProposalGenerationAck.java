package org.javaguru.travel.insurance.core.messagebroker.proposalack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalGenerationAck {

    private String agreementUuid;
    private String proposalFilePath;

}
