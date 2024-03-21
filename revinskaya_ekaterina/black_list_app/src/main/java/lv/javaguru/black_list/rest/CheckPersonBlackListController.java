package lv.javaguru.black_list.rest;

import lv.javaguru.black_list.core.api.command.CoreCommand;
import lv.javaguru.black_list.core.api.command.CoreResult;
import lv.javaguru.black_list.core.api.dto.PersonDTO;
import lv.javaguru.black_list.core.servises.CheckPersonService;
import lv.javaguru.black_list.dto.CheckPersonInBlackListCoreResponse;
import lv.javaguru.black_list.dto.CheckPersonInBlackListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/blacklist")
public class CheckPersonBlackListController {
    @Autowired
    private CheckPersonService service;
    @PostMapping(path = "/person/check",
            consumes = "application/json",
            produces = "application/json")
    CheckPersonInBlackListCoreResponse checkPerson(@RequestBody CheckPersonInBlackListRequest request){
        PersonDTO personDTO = request.getPersonDTO();
        CoreResult result = service.checkPerson(new CoreCommand(personDTO));
        return result.hasErrors() ? new CheckPersonInBlackListCoreResponse(result.getErrors())
                : new CheckPersonInBlackListCoreResponse(personDTO);
    }

}
