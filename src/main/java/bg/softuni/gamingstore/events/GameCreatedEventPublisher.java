package bg.softuni.gamingstore.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GameCreatedEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public GameCreatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(String title, BigDecimal price) {
        GameCreatedEvent event = new GameCreatedEvent(title, price);
        applicationEventPublisher.publishEvent(event);
    }
}
