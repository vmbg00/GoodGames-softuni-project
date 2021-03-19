package bg.softuni.gamingstore.models.services;

import bg.softuni.gamingstore.models.entities.enums.GenreEnum;
import org.springframework.web.multipart.MultipartFile;

public class NewsAddServiceModel {
    private String title;
    private String description;
    private GenreEnum genre;
    private MultipartFile image;

    public NewsAddServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public NewsAddServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public NewsAddServiceModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public NewsAddServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
