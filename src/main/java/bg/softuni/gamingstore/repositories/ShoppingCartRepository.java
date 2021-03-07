package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    List<ShoppingCartEntity> findAllByUser(UserEntity userEntity);
}
