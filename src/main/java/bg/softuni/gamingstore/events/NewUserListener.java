package bg.softuni.gamingstore.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewUserListener {

    private final Logger LOGGER = LoggerFactory.getLogger(NewUserEvent.class);

    @EventListener(NewUserEvent.class)
    public void onStudentEvent(NewUserEvent event) {
        LOGGER.info("New user event received!");
        LOGGER.info(event.toString());
    }
}
