package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    List<NewsEntity> findAllByOrderByIdDesc();
}
