package bg.softuni.gamingstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
public class GamingStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamingStoreApplication.class, args);
    }

}
