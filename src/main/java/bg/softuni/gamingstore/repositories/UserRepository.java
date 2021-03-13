package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u FROM UserEntity as u where u.roles.size = 1")
    List<UserEntity> findAllWithUserRoles();

    @Query("SELECT u FROM UserEntity as u where u.roles.size = 2")
    List<UserEntity> findAllWithUserAndAdminRoles();
}
