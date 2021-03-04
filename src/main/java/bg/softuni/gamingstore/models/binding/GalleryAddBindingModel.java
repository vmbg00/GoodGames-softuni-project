package bg.softuni.gamingstore.models.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GalleryAddBindingModel {

    private String title;
    private String description;
    private String url;

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

    @NotBlank(message = "Cannot be blank")
    public String getUrl() {
        return url;
    }

    public GalleryAddBindingModel setUrl(String url) {
        this.url = url;
        return this;
    }
}
