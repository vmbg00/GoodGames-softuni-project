package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicturesRepository extends JpaRepository<PictureEntity, Long> {
    List<PictureEntity> findAllByOrderByIdDesc();
}
