package lv.javaguru.black_list.core.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.black_list.core.api.dto.PersonDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoreCommand {
    PersonDTO personDTO;
}
