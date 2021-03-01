package bg.softuni.gamingstore.models.entities;

import bg.softuni.gamingstore.models.entities.enums.GenreEnum;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "games")
public class GameEntity extends BaseEntity{

    private String name;
    private String description;
    private BigDecimal price;
    private GenreEnum genre;

    public GameEntity() {
    }

    public String getName() {
        return name;
    }

    public GameEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public GameEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public GameEntity setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }
}
