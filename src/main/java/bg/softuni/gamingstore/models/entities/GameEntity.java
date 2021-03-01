package bg.softuni.gamingstore.models.entities;

import bg.softuni.gamingstore.models.entities.enums.GenreEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "games")
public class GameEntity extends BaseEntity{

    private String name;
    private String description;
    private BigDecimal price;
    private String platform;
    private GenreEnum genre;

    public GameEntity() {
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public GameEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public GameEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    public String getPlatform() {
        return platform;
    }

    public GameEntity setPlatform(String platform) {
        this.platform = platform;
        return this;
    }


    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public GameEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Enumerated(value = EnumType.STRING)
    public GenreEnum getGenre() {
        return genre;
    }

    public GameEntity setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }
}
