package bg.softuni.gamingstore.models.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GalleryAddBindingModel {

    private String title;
    private String description;
    private MultipartFile url;

    public GalleryAddBindingModel() {
    }

    @NotBlank(message = "Cannot be blank")
    @Size(min = 5, message = "Needs to be at least 5 characters")
    public String getTitle() {
        return title;
    }

    public GalleryAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotBlank(message = "Cannot be blank")
    @Size(min = 10, message = "Needs to be at least 10 characters")
    public String getDescription() {
        return description;
    }

    public GalleryAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    public MultipartFile getUrl() {
        return url;
    }

    public GalleryAddBindingModel setUrl(MultipartFile url) {
        this.url = url;
        return this;
    }
}
