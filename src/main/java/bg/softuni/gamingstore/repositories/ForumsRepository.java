package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.ForumPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumsRepository extends JpaRepository<ForumPosts, Long> {
}
