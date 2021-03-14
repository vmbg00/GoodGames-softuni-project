package bg.softuni.gamingstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GamingStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamingStoreApplication.class, args);
    }

}
