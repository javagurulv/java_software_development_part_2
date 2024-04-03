package org.javaguru.travel.insurance.core.blacklist;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"h2"})
class BlackListPersonCheckServiceStubImpl implements BlackListPersonCheckService {

    private static final Logger logger = LoggerFactory.getLogger(BlackListPersonCheckServiceStubImpl.class);

    @Override
    public boolean isPersonBlacklisted(PersonDTO personDTO) {
        logger.info("BlackList stub invoked! Always return false!");
        return false;
    }

}
