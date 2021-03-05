package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.RoleEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.entities.enums.RoleEnums;
import bg.softuni.gamingstore.models.services.RegisterServiceModel;
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

import java.util.List;
import java.util.Optional;

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
    public void register(RegisterServiceModel newUser) {
        UserEntity userEntity = this.modelMapper.map(newUser, UserEntity.class);

        RoleEntity role = this.rolesRepository.findByName(RoleEnums.USER);

        userEntity
                .setRoles(List.of(role))
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
    public boolean userNameExists(String username) {
       return this.userRepository.findByUsername(username).isPresent();
    }
}
