package bg.softuni.gamingstore.models.binding;

import bg.softuni.gamingstore.models.entities.enums.GenreEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class NewsAddBindingModel {
    private String title;
    private String description;
    private GenreEnum genre;
    private MultipartFile image;

    public NewsAddBindingModel() {
    }

    @Size(min = 10, message = "Title must be at least 10 characters long")
    @NotNull
    public String getTitle() {
        return title;
    }

    public NewsAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @Size(min = 20, message = "Title must be at least 20 characters long")
    @NotNull
    public String getDescription() {
        return description;
    }

    public NewsAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    public GenreEnum getGenre() {
        return genre;
    }

    public NewsAddBindingModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    @NotNull
    public MultipartFile getImage() {
        return image;
    }

    public NewsAddBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
