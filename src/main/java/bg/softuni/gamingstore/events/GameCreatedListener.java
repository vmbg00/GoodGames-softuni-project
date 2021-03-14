package bg.softuni.gamingstore.events;

import bg.softuni.gamingstore.services.impl.GameServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GameCreatedListener {

    private Logger LOGGER = LoggerFactory.getLogger(GameCreatedEvent.class);

    @EventListener(GameCreatedEvent.class)
    public void onStudentEvent(GameCreatedEvent event) {
        LOGGER.info("Game created event received!");
        LOGGER.info(event.toString());
    }
}
