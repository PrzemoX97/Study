package com.demo.springboot.rest;

import com.demo.springboot.domain.dto.ErrorDto;
import com.demo.springboot.domain.dto.UserDataDto;
import com.demo.springboot.domain.dto.ErrorMessages;
import com.demo.springboot.domain.dto.FileData;
import com.demo.springboot.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PdfGeneratorApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfGeneratorApiController.class);

    private static final String PATH = "D:\\pdf\\"; // dla windows


    @Autowired
    private FileService fileService;

    @CrossOrigin
    @RequestMapping(value = "/server-test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String, String>> serverTest() {
        LOGGER.info("--- run server status");

        Map<String, String> serverTestMessage = new HashMap<>();
        serverTestMessage.put("server-status", "RUN :-)");

        return new ResponseEntity<>(serverTestMessage, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/files/find-all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<FileData>> findAll() {
        LOGGER.info("--- find all");

        final List<FileData> files = fileService.findAll(PATH);

        return files.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(files, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/files/create-file", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createFile(@RequestBody UserDataDto userDataDto) {

        final FileData fileData = fileService.createFile(userDataDto, PATH);

        return fileData != null ?
                new ResponseEntity<>(fileData, HttpStatus.CREATED) :
                new ResponseEntity<>(new ErrorDto(ErrorMessages.ERROR_PATH.getErrorMessage()), ErrorMessages.ERROR_PATH.getErrorCode());
    }
}
