package vn.edu.hust.pthtwat.pthtwat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import vn.edu.hust.pthtwat.pthtwat.model.Greeting;
import vn.edu.hust.pthtwat.pthtwat.model.HelloMessage;
import vn.edu.hust.pthtwat.pthtwat.service.GreetingService;

import java.security.Principal;

@Controller
public class GreetingController {

    private final static Logger log = LoggerFactory.getLogger(GreetingController.class);

    private final GreetingService greetingService;

    GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @MessageMapping("/hello")
    @SendToUser("/topic/greetings")
    public Greeting greeting(HelloMessage message, Principal principal) throws Exception {
        log.info("Received greeting message {} from {}", message, principal.getName());
        greetingService.addUserName(principal.getName());
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}