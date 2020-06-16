package vn.edu.hust.pthtwat.pthtwat.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.edu.hust.pthtwat.pthtwat.service.GreetingService;


@Component
public class Scheduler {
    private final static Logger log = LoggerFactory.getLogger(Scheduler.class);

    private final GreetingService greetingService;

    Scheduler(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

//    @Scheduled(fixedRateString = "6000", initialDelayString = "0")
//    public void schedulingTask() {
//        log.info("Send messages due to schedule");
//        greetingService.sendMessages();
//    }
}
