package bg.softuni.gamingstore.models.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCartEntity extends BaseEntity{

    private UserEntity user;
    private List<GameEntity> games;

    public ShoppingCartEntity() {
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public ShoppingCartEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    @ManyToMany
    public List<GameEntity> getGames() {
        return games;
    }

    public ShoppingCartEntity setGames(List<GameEntity> games) {
        this.games = games;
        return this;
    }
}
