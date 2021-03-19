package bg.softuni.gamingstore.models.entities;

import bg.softuni.gamingstore.models.entities.enums.GenreEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class NewsEntity extends BaseEntity{
    private String title;
    private String description;
    private LocalDateTime date;
    private GenreEnum genre;
    private String image;
    private UserEntity userEntity;

    public NewsEntity() {
    }

    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public NewsEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public NewsEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public NewsEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column
    public LocalDateTime getDate() {
        return date;
    }

    public NewsEntity setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    @Enumerated(value = EnumType.STRING)
    public GenreEnum getGenre() {
        return genre;
    }

    public NewsEntity setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    @Column
    public String getImage() {
        return image;
    }

    public NewsEntity setImage(String image) {
        this.image = image;
        return this;
    }
}
