package org.javaguru.travel.insurance.core.blacklist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlackListedPersonCheckResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    private String personCode;
    private Boolean blacklisted;

    public BlackListedPersonCheckResponse(List<ValidationError> errors) {
        super(errors);
    }

}
