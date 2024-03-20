package lv.javaguru.black_list.core.servises;

import lv.javaguru.black_list.core.api.command.CoreCommand;
import lv.javaguru.black_list.core.api.command.CoreResult;

public interface CheckPersonService {
    CoreResult checkPerson(CoreCommand coreCommand);
}
