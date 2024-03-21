package lv.javaguru.black_list.core.repositories;


import lv.javaguru.black_list.core.domain.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query("select case when count(p) != 0 then true else false end "+
            "from PersonEntity p where p.personalCode = :personalCode")
    boolean existByPersonalCode(
            @Param("personalCode") String personalCode
    );
}
