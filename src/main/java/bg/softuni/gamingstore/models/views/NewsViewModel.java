package bg.softuni.gamingstore.models.views;

import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.entities.enums.GenreEnum;

import java.time.LocalDateTime;

public class NewsViewModel {
    private String title;
    private String description;
    private String date;
    private GenreEnum genre;
    private String image;
    private UserEntity userEntity;

    public NewsViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public NewsViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDate() {
        return date;
    }

    public NewsViewModel setDate(String date) {
        this.date = date;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public NewsViewModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getImage() {
        return image;
    }

    public NewsViewModel setImage(String image) {
        this.image = image;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public NewsViewModel setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
