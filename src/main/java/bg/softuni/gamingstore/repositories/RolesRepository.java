package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.RoleEntity;
import bg.softuni.gamingstore.models.entities.enums.RoleEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(RoleEnums user);

    RoleEntity getByName(RoleEnums roleEnums);
}
