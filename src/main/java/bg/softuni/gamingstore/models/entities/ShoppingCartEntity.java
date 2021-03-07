package bg.softuni.gamingstore.models.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCartEntity extends BaseEntity{

    private UserEntity user;
    private GameEntity games;

    public ShoppingCartEntity() {
    }

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public UserEntity getUser() {
        return user;
    }

    public ShoppingCartEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public GameEntity getGames() {
        return games;
    }

    public ShoppingCartEntity setGames(GameEntity games) {
        this.games = games;
        return this;
    }
}
