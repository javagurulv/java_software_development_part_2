package org.javaguru.blacklist.core.api;

import lombok.*;

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
