package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.Response;
import java.time.LocalDate;

@RestController
public class BusController {

    @Autowired
    BusService busService;


    @RequestMapping("/alertUser")
    public ResponseEntity alertUser(@RequestParam("date")@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date, @RequestParam("time")String time){

        busService.alertUser(date);
        return new ResponseEntity(HttpStatus.OK);
    }

}
