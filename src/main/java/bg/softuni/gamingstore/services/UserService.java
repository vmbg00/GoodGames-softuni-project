package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.services.RegisterServiceModel;

public interface UserService {
    void register(RegisterServiceModel map);

    boolean authenticate(String username, String password);

    boolean userNameExists(String username);
}
