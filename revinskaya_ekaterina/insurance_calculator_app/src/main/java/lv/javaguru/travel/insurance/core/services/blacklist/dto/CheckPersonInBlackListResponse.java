package lv.javaguru.travel.insurance.core.services.blacklist.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckPersonInBlackListResponse extends CoreResponse {
    @JsonAlias("personFirstName")
    private String firstName;
    @JsonAlias("personLastName")
    private String lastName;
    @JsonAlias("personCode")
    private String personalCode;
    @JsonAlias("blackListed")
    private Boolean blackListed;
    public CheckPersonInBlackListResponse(List<ValidationErrorDTO> errors){
        super(errors);
    }
}
