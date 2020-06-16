package vn.edu.hust.pthtwat.pthtwat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@Configuration
@EnableScheduling
public class PthtwatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PthtwatApplication.class, args);
    }

}
