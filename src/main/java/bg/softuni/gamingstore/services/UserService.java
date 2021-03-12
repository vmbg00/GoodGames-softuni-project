package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.services.ChangeUserRoleServiceModel;
import bg.softuni.gamingstore.models.services.RegisterServiceModel;
import bg.softuni.gamingstore.models.views.UserOwnedGamesViewModel;

import java.util.List;

public interface UserService {
    void register(RegisterServiceModel map);

    boolean userNameExists(String username);

    UserEntity getUserEntity();

    List<UserOwnedGamesViewModel> getAllUserGames();

    List<UserEntity> getAllUsers();

    void changeUserRole(ChangeUserRoleServiceModel map);
}
