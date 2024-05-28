package org.javaguru.travel.insurance.core.blacklist;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;

public interface BlackListPersonCheckService {

    boolean isPersonBlacklisted(PersonDTO personDTO);

}
