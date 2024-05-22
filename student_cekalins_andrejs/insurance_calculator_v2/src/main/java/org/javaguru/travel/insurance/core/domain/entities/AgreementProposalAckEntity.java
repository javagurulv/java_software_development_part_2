package org.javaguru.travel.insurance.core.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.YesNoConverter;

@Entity
@Table(name = "agreement_proposals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgreementProposalAckEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agreement_uuid", nullable = false)
    private String agreementUuid;

    @Column(name = "already_generated", nullable = false)
    @Convert(converter = YesNoConverter.class)
    private Boolean alreadyGenerated;

    @Column(name = "proposal_file_path", nullable = false)
    private String proposalFilePath;

}
