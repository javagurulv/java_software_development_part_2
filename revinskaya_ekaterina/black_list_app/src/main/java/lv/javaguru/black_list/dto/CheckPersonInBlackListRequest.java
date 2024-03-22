package lv.javaguru.black_list.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.black_list.core.api.dto.PersonDTO;

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
