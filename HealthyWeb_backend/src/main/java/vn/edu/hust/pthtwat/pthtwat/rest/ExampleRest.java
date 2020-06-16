package vn.edu.hust.pthtwat.pthtwat.rest;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExampleRest {
    @GetMapping("/findZipCode")
    public void testRestApi(@RequestHeader(name = "Access-Token") String accessToken,
                                                       @RequestParam("code") String code) {
    }
}
