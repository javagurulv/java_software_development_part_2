package lv.javaguru.travel.insurance.core.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveFileResponse {
    @JsonAlias("file_path")
    String filePath;
    @JsonAlias("agreement_uuid")
    String agreementUuid;
}
