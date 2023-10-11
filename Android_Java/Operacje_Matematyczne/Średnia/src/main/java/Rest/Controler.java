package Rest;
import Files.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/api")
public class Controler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controler.class);

    @RequestMapping(value = "/math/digits/{numbers}", method = RequestMethod.GET)
    public ResponseEntity<Average> av(@PathVariable("numbers")String numbers)
    {
        final Average av = new Average(numbers);
        LOGGER.info("###average : {}", av.getAverage());
        return new ResponseEntity<Average>(av, HttpStatus.OK);
    }
}
