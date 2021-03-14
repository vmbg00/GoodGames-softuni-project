package bg.softuni.gamingstore.events.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

    @Scheduled(fixedRate = 30000)
    public void scheduleTaskWithFixedRate() {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("Program is working: Timestamp - " + time);
    }
}
