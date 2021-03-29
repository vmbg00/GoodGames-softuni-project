package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.RoleEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.entities.enums.RoleEnums;
import bg.softuni.gamingstore.models.services.ChangeUserRoleServiceModel;
import bg.softuni.gamingstore.models.services.DeleteUserServiceModel;
import bg.softuni.gamingstore.models.services.DemoteUserServiceModel;
import bg.softuni.gamingstore.models.services.RegisterServiceModel;
import bg.softuni.gamingstore.models.views.UserOwnedGamesViewModel;
import bg.softuni.gamingstore.repositories.RolesRepository;
import bg.softuni.gamingstore.repositories.UserRepository;
import bg.softuni.gamingstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final GoodGamesUserServiceImpl goodGamesUserService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RolesRepository rolesRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, GoodGamesUserServiceImpl goodGamesUserService) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.goodGamesUserService = goodGamesUserService;
    }


    @Override
    public RegisterServiceModel register(RegisterServiceModel newUser) {
        UserEntity userEntity = this.modelMapper.map(newUser, UserEntity.class);

        RoleEntity role = this.rolesRepository.findByName(RoleEnums.USER);

        userEntity
                .setRoles(Set.of(role))
                .setGames(null)
                .setPassword(this.passwordEncoder.encode(newUser.getPassword()));

        userEntity = this.userRepository.save(userEntity);

        UserDetails princip = this.goodGamesUserService.loadUserByUsername(userEntity.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                princip,
                userEntity.getPassword(),
                princip.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return newUser;
    }

    @Override
    public boolean userNameExists(String username) {
       return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserEntity getUserEntity() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        UserEntity user = this.userRepository.findByUsername(username).get();
        return user;
    }

    @Override
    public List<UserOwnedGamesViewModel> getAllUserGames() {
        Optional<UserEntity> username = this.userRepository.findByUsername(getUserEntity().getUsername());

        List<UserOwnedGamesViewModel> games = new ArrayList<>();
        for (GameEntity game : username.get().getGames()) {
            UserOwnedGamesViewModel gamesViewModel = new UserOwnedGamesViewModel();

            gamesViewModel.setTitle(game.getName());
            gamesViewModel.setPlatform(game.getPlatform().toString());
            gamesViewModel.setGenre(game.getGenre().toString());
            gamesViewModel.setBoughtFor(game.getPrice().toString());

            games.add(gamesViewModel);
        }

        return games;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAllWithUserRoles();
    }

    @Override
    public List<UserEntity> getAllWithAdminRoles() {
        return this.userRepository.findAllWithUserAndAdminRoles();
    }

    @Override
    public void changeUserRole(ChangeUserRoleServiceModel map) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(map.getUsername());

        RoleEntity byName = this.rolesRepository.findByName(RoleEnums.ADMIN);

        byUsername.get().getRoles().add(byName);

        this.userRepository.saveAndFlush(byUsername.get());
    }

    @Override
    public void demoteUser(DemoteUserServiceModel userRoleServiceModel) {
        Optional<UserEntity> username = this.userRepository.findByUsername(userRoleServiceModel.getUsername());

        RoleEntity role = this.rolesRepository.findByName(RoleEnums.ADMIN);

        username.get().getRoles().remove(role);

        this.userRepository.save(username.get());
    }

    @Override
    public List<UserEntity> getAllUsersAndAdmins() {
        return this.userRepository.getAllUsersAndAdmins();
    }

    @Override
    public void deleteUser(DeleteUserServiceModel userServiceModel) {
        Optional<UserEntity> user = this.userRepository.findByUsername(userServiceModel.getUsername());

        this.userRepository.delete(user.get());
    }

    @Override
    public Optional<UserEntity> findByUsername(String name) {
        return this.userRepository.findByUsername(name);
    }

    @Override
    public List<UserEntity> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Long getIdByUsername(String username) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);

        try {
            return byUsername.get().getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserEntity getByEmail(String email) {
        Optional<UserEntity> userEntity = this.userRepository.findByEmail(email);

        try {
            return userEntity.get();
        } catch (Exception e){
            return null;
        }
    }
}
