package bg.softuni.gamingstore.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GameCreatedListener {

    private final Logger LOGGER = LoggerFactory.getLogger(GameCreatedEvent.class);

    @EventListener(GameCreatedEvent.class)
    public void onStudentEvent(GameCreatedEvent event) {
        LOGGER.info("Game created event received!");
        LOGGER.info(event.toString());
    }
}
