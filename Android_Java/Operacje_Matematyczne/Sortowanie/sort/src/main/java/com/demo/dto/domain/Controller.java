package com.demo.dto.domain;

import com.demo.dto.demo.dto.dto.SortDto;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/api")
public class Controller {
    private String numb;
    private SortDto mean;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/math/sort/{numbers}", method=RequestMethod.GET)
    public ResponseEntity<SortDto> getMean(@PathVariable ("numbers") String numbers) {
        mean = new SortDto();
        numb = mean.sort(numbers);
        mean = new SortDto(numb);

        LOGGER.info("Mean: ", mean.getNumbers());
        return new ResponseEntity<>(mean, HttpStatus.OK);
    }
}
