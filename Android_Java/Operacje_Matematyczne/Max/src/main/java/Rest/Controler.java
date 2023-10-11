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

    @RequestMapping(value = "/math/getMax/{numbers}", method = RequestMethod.GET)
    public ResponseEntity<Max> max(@PathVariable("numbers")String numbers)
    {
        final Max mx = new Max(numbers);
        LOGGER.info("###Max : {}", mx.getMax());
        return new ResponseEntity<Max>(mx, HttpStatus.OK);
    }

}
