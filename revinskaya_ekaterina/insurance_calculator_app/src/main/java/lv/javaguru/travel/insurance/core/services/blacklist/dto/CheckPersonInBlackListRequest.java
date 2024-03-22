package lv.javaguru.travel.insurance.core.services.blacklist.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CheckPersonInBlackListRequest {
    @JsonAlias("personFirstName")
    private String firstName;
    @JsonAlias("personLastName")
    private String lastName;
    @JsonAlias("personCode")
    private String personalCode;

}
