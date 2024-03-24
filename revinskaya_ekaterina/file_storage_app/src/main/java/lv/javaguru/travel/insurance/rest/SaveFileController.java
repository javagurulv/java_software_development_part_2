package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.dto.SaveFileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping(value = "/pdf-file-saver")
public class SaveFileController {

    @Value("${proposals.directory.path}")
    private String proposalsDirectoryPath;
    @PostMapping(path = "/{uuid}",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = "application/json"
            )
    SaveFileResponse saveFile(@RequestParam("file") MultipartFile file, @PathVariable String uuid) throws IOException {
        File receiveFile = new File(proposalsDirectoryPath + "/agreement-" + uuid + ".pdf");
        file.transferTo(receiveFile);
        return new SaveFileResponse("bucket@agreement-"+uuid+"-pdf_file_storage", uuid);
    }
}

