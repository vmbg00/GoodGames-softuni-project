package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    List<ShoppingCartEntity> findAllByUser(UserEntity userEntity);

    @Transactional
    @Modifying
    @Query("DELETE FROM ShoppingCartEntity as s WHERE s.games.id = :id")
    void deleteShoppingCartEntityByGames_Id(Long id);
}
