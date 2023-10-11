package com.demo.dto.domain;

import com.demo.dto.demo.dto.dto.MeanDto;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/api")
public class Controller {
    private String text;
    private char character;
    private int length;
    private MeanDto mean;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/text/length", method=RequestMethod.GET)
    public ResponseEntity<MeanDto> getMean(@RequestParam (value = "text", required = false) String text,
    @RequestParam(value = "character", required = false) char character) {
        mean = new MeanDto();
        length = mean.countLength(text, character);
        mean.setLength(length);
        LOGGER.info("Mean: {}", mean.getLength());
        return new ResponseEntity<>(mean, HttpStatus.OK);
    }
}
