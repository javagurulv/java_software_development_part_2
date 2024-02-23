package lv.javaguru.travel.insurance.core.repositories.entity;

import lv.javaguru.travel.insurance.core.domain.entity.AgreementEntity;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
public interface AgreementRepository extends JpaRepository<AgreementEntity, Long> {
    @Query("SELECT CASE WHEN COUNT(agr) != 0 THEN true ELSE false END "
            + "FROM AgreementEntity agr WHERE agr.uuid = :uuid"
    )
    boolean existByUuid(@Param("uuid") String uuid);

    Optional<AgreementEntity> findByUuid(@Param("uuid") String uuid);
    @Query("SELECT agr.id FROM AgreementEntity agr WHERE agr.alreadyExported = false")
    List<Long> findNotAlreadyExportedId(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE AgreementEntity agr SET agr.alreadyExported = true WHERE agr.id = :id ")
    void updateColumnValueById(@Param("id") Long id);
}
