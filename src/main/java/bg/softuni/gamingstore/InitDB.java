package bg.softuni.gamingstore;

import bg.softuni.gamingstore.models.entities.RoleEntity;
import bg.softuni.gamingstore.models.entities.enums.RoleEnums;
import bg.softuni.gamingstore.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitDB implements CommandLineRunner {

    private final RolesRepository rolesRepository;

    @Autowired
    public InitDB(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
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
    }
}
