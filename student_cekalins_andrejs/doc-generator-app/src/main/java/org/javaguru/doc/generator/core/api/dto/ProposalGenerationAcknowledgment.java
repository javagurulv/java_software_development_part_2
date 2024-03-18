package org.javaguru.doc.generator.core.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProposalGenerationAcknowledgment {
    private String agreementUuid;
    private String proposalFilePath;

}
