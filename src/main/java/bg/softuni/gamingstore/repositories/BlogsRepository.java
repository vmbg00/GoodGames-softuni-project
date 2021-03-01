package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsRepository extends JpaRepository<BlogEntity, Long> {
}
