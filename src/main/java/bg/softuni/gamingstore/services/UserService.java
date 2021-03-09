package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.services.RegisterServiceModel;

public interface UserService {
    void register(RegisterServiceModel map);

    boolean userNameExists(String username);

    UserEntity getUserEntity();
}
