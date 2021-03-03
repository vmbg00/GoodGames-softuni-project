package bg.softuni.gamingstore.models.binding;

import javax.validation.constraints.NotBlank;

public class GalleryAddBindingModel {

    private String title;
    private String description;
    private String url;

    public GalleryAddBindingModel() {
    }

    @NotBlank(message = "Cannot be blank")
    public String getTitle() {
        return title;
    }

    public GalleryAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotBlank(message = "Cannot be blank")
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
