package bg.softuni.gamingstore.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewUserListener {

    @EventListener(NewUserEvent.class)
    public void onStudentEvent(NewUserEvent event) {
        System.out.println("New user register event received!");
        System.out.println(event);
    }
}
