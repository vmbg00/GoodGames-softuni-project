package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends JpaRepository<GameEntity, Long> {
    GameEntity findByName(String name);
}
