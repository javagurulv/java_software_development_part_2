package lv.javaguru.black_list.repositories;


import lv.javaguru.black_list.domain.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Optional<PersonEntity> findByPersonalCode(
            @Param("personalCode") String personCode
    );
}
