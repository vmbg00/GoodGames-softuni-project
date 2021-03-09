package bg.softuni.gamingstore.repositories;

import bg.softuni.gamingstore.models.entities.BillingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingHistoryRepository extends JpaRepository<BillingHistoryEntity, Long> {
}
