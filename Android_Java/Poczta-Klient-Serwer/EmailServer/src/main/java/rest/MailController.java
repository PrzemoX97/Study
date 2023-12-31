package rest;

import com.demo.springboot.Mail;
import com.demo.springboot.FileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {


    private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);




    @CrossOrigin
    @RequestMapping(value = "/email/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createFile(@RequestBody Mail mail) {

        try{
            FileDTO.addUserToFile(mail);
        }catch(Exception err){
            err.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }








}
