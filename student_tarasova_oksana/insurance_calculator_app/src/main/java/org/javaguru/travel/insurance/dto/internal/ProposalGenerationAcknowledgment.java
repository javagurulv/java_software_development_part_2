package org.javaguru.travel.insurance.dto.internal;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalGenerationAcknowledgment {

    private String agreementUuid;
    private String proposalFilePath;

}
