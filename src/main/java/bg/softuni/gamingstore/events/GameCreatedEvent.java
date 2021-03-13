package bg.softuni.gamingstore.events;

import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

public class GameCreatedEvent extends ApplicationEvent {
    private String title;
    private BigDecimal price;

    public GameCreatedEvent(String title, BigDecimal price) {
        super(title);
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public GameCreatedEvent setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public GameCreatedEvent setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Game created is -> Name: " + title + " with price: " + price;
    }
}
