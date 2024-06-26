package lv.javaguru.travel.insurance.core.domain.entity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.type.YesNoConverter;

@Entity
@Table(name = "agreements_ack")
@Getter
@Setter
public class AgreementAckEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "agreement_uuid", nullable = false)
    private String agreementUuid;
    @Column(name = "already_exported", nullable = false)
    @Convert(converter = YesNoConverter.class)
    private Boolean alreadyExported;
    @Column(name = "file_path", nullable = false)
    private String filePath;

}
