package Rest;
import Files.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api")

public class Controler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controler.class);



    @RequestMapping(value = "/math/isPrime/{number}", method = RequestMethod.GET)
    public ResponseEntity<FirstNumber> fn(@PathVariable("number")int number)
    {
        final FirstNumber fn = new FirstNumber(number);
        if(fn.getFirstNumber().equals("true")) {
            LOGGER.info("###prime : {}", fn.getFirstNumber());
            return new ResponseEntity<FirstNumber>(fn, HttpStatus.OK);
        }
        else {
            LOGGER.info("###prime : {}", fn.getFirstNumber());
            return new ResponseEntity<FirstNumber>(fn, HttpStatus.NOT_FOUND);
        }


    }

}
