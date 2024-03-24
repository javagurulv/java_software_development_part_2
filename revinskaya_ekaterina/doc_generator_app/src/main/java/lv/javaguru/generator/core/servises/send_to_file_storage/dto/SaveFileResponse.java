package lv.javaguru.generator.core.servises.send_to_file_storage.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveFileResponse {
    @JsonAlias("file_path")
    String filePath;
    @JsonAlias("agreement_uuid")
    String agreementUuid;
}
