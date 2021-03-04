package bg.softuni.gamingstore;

import bg.softuni.gamingstore.models.entities.RoleEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.entities.enums.RoleEnums;
import bg.softuni.gamingstore.repositories.RolesRepository;
import bg.softuni.gamingstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitDB implements CommandLineRunner {

    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public InitDB(RolesRepository rolesRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolesRepository.count() == 0){
            RoleEntity admin = new RoleEntity();
            admin.setName(RoleEnums.ADMIN);
            admin.setDescription("Big boss");

            RoleEntity user = new RoleEntity();
            user.setName(RoleEnums.USER);
            user.setDescription("Peasant");

            this.rolesRepository.saveAll(List.of(admin, user));
        }

        if (userRepository.count() == 0){
            UserEntity userEntity = new UserEntity();

            RoleEntity admin = this.rolesRepository.findByName(RoleEnums.ADMIN);
            RoleEntity user = this.rolesRepository.findByName(RoleEnums.USER);

            userEntity
                    .setUsername("admin")
                    .setPassword(this.passwordEncoder.encode("12345"))
                    .setEmail("admin@gmail.com")
                    .setRoles(List.of(admin, user))
                    .setGames(null);

            userRepository.save(userEntity);
        }
    }
}
