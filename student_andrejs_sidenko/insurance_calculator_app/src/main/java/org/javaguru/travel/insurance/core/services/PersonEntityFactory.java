package org.javaguru.travel.insurance.core.services;

import java.util.Optional;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import org.javaguru.travel.insurance.core.repositories.entities.PersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
class PersonEntityFactory {

    @Autowired private PersonEntityRepository repository;

    PersonEntity createPersonEntity(PersonDTO personDTO) {
        Optional<PersonEntity> personOpt = repository.findBy(
                personDTO.getPersonFirstName(),
                personDTO.getPersonLastName(),
                personDTO.getPersonCode());
        if (personOpt.isPresent()) {
            return personOpt.get();
        } else {
            PersonEntity person = new PersonEntity();
            person.setFirstName(personDTO.getPersonFirstName());
            person.setLastName(personDTO.getPersonLastName());
            person.setPersonCode(personDTO.getPersonCode());
            person.setBirthDate(personDTO.getPersonBirthDate());
            return repository.save(person);
        }
    }

}
