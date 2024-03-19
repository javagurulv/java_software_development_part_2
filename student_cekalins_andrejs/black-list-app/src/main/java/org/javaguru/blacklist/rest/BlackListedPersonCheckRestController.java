package org.javaguru.blacklist.rest;

import com.google.common.base.Stopwatch;
import org.javaguru.blacklist.core.api.BlackListedPersonCoreCommand;
import org.javaguru.blacklist.core.api.BlackListedPersonCoreResult;
import org.javaguru.blacklist.core.sevices.BlackListedPersonService;
import org.javaguru.blacklist.dto.BlackListedPersonCheckRequest;
import org.javaguru.blacklist.dto.BlackListedPersonCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blacklist/person/check")
public class BlackListedPersonCheckRestController {
    @Autowired private BlackListedPersonRequestLogger requestLogger;
    @Autowired private BlackListedPersonResponseLogger responseLogger;
    @Autowired private RestRequestExecutionTimeLogger executionTimeLogger;
    @Autowired private BlackListedPersonService blackListedPersonService;
    @Autowired private DtoConverter dtoConverter;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public BlackListedPersonCheckResponse checkPerson(@RequestBody BlackListedPersonCheckRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        BlackListedPersonCheckResponse response = processRequest(request);
        executionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }

    private BlackListedPersonCheckResponse processRequest(BlackListedPersonCheckRequest request) {
        requestLogger.log(request);
        BlackListedPersonCoreCommand coreCommand = dtoConverter.buildCoreCommand(request);
        BlackListedPersonCoreResult coreResult = blackListedPersonService.check(coreCommand);
        BlackListedPersonCheckResponse response = dtoConverter.buildResponse(coreResult);
        responseLogger.log(response);
        return response;
    }

}
