package com.demo.dto.domain;

import com.demo.dto.demo.dto.dto.ReverseDto;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/api")
public class Controller {
    private String reverse;
    private ReverseDto mean;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    private List<Integer> l;

    @RequestMapping(value = "/text/reverse", method=RequestMethod.GET)
    public ResponseEntity<ReverseDto> getMean(@RequestParam (value = "text", required = false) String text) {

        System.out.println(getNumbers(text));

        LOGGER.info("Moj reverse: {}", reverse);

        mean = new ReverseDto(reverse);
        LOGGER.info("Mean: {}", mean.getReverse());
        return new ResponseEntity<>(mean, HttpStatus.OK);

    }

    private static List<Integer> getNumbers(String s) {
        List<Integer> l = new ArrayList<Integer>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                String  num = "";
                while (i < chars.length && Character.isDigit(chars[i])) {
                    num += chars[i++];
                }
                l.add(Integer.parseInt(num));
            }
        }
       int r= l.get(2);
        System.out.println(r);
        return r;
    }
}
