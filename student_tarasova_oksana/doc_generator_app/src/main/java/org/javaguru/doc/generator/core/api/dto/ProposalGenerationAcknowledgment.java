package org.javaguru.doc.generator.core.api.dto;

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
