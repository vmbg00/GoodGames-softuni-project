package bg.softuni.gamingstore.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NewUserEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public NewUserEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(String name) {
        NewUserEvent event = new NewUserEvent(name);
        applicationEventPublisher.publishEvent(event);
    }
}
