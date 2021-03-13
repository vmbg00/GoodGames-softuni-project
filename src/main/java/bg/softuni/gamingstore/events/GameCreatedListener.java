package bg.softuni.gamingstore.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GameCreatedListener {

    @EventListener(GameCreatedEvent.class)
    public void onStudentEvent(GameCreatedEvent event) {
        System.out.println("Game created event received!");
        System.out.println(event);
    }
}
