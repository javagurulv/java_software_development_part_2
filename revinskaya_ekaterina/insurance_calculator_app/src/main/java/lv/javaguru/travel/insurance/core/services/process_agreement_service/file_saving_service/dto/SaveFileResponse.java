package lv.javaguru.travel.insurance.core.services.process_agreement_service.file_saving_service.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveFileResponse {
    @JsonAlias("file_path")
    String filePath;
    @JsonAlias("agreement_uuid")
    String agreementUuid;
}
