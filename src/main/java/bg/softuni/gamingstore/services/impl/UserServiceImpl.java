package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.RoleEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.entities.enums.RoleEnums;
import bg.softuni.gamingstore.models.services.LoginServiceModel;
import bg.softuni.gamingstore.models.services.RegisterServiceModel;
import bg.softuni.gamingstore.repositories.RolesRepository;
import bg.softuni.gamingstore.repositories.UserRepository;
import bg.softuni.gamingstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RolesRepository rolesRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void register(RegisterServiceModel newUser) {
        UserEntity userEntity = this.modelMapper.map(newUser, UserEntity.class);

        RoleEntity role = this.rolesRepository.findByName(RoleEnums.USER);

        userEntity
                .setRoles(List.of(role))
                .setGames(null)
                .setPassword(this.passwordEncoder.encode(newUser.getPassword()));

        this.userRepository.save(userEntity);
    }

    @Override
    public boolean authenticate(String username, String password) {
        Optional<UserEntity> user = this.userRepository.findByUsername(username);

        if (user.isEmpty()){
            return false;
        }
        else {
            return user.get().getPassword().equals(password);
        }
    }

    @Override
    public void login(LoginServiceModel map) {

    }
}
