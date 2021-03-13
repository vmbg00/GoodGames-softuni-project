package bg.softuni.gamingstore.events;

import org.springframework.context.ApplicationEvent;

public class NewUserEvent extends ApplicationEvent {
    private String username;

    public NewUserEvent(String username) {
        super(username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public NewUserEvent setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        return "New registered user is -> " + username;
    }
}
