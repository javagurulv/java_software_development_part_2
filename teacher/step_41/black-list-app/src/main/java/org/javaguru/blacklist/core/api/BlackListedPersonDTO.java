package org.javaguru.blacklist.core.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlackListedPersonDTO {

    private String personFirstName;

    private String personLastName;

    private String personCode;

    private Boolean blackListed;

}
