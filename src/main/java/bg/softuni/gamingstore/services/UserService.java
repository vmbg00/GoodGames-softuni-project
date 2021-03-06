package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.services.ChangeUserRoleServiceModel;
import bg.softuni.gamingstore.models.services.DeleteUserServiceModel;
import bg.softuni.gamingstore.models.services.DemoteUserServiceModel;
import bg.softuni.gamingstore.models.services.RegisterServiceModel;
import bg.softuni.gamingstore.models.views.UserOwnedGamesViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    RegisterServiceModel register(RegisterServiceModel map);

    boolean userNameExists(String username);

    UserEntity getUserEntity();

    List<UserOwnedGamesViewModel> getAllUserGames();

    List<UserEntity> getAllUsers();
    
    List<UserEntity> getAllWithAdminRoles();

    void changeUserRole(ChangeUserRoleServiceModel map);

    void demoteUser(DemoteUserServiceModel map);

    List<UserEntity> getAllUsersAndAdmins();

    void deleteUser(DeleteUserServiceModel map);

    Optional<UserEntity> findByUsername(String name);

    List<UserEntity> getAll();

    Long getIdByUsername(String username);

    UserEntity getByEmail(String email);
}
