package lv.javaguru.black_list.core.validation.util;

import lombok.Value;
import lv.javaguru.black_list.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:errorCodes.properties")
public class ValidationErrorConstructor {
    @Autowired
    private Environment environment;
    public ValidationErrorDTO constructError(String errorCode){
        return new ValidationErrorDTO(errorCode, environment.getProperty(errorCode));
    }
}
