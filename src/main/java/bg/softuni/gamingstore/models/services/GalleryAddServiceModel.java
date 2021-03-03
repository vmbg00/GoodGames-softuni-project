package bg.softuni.gamingstore.models.services;

public class GalleryAddServiceModel {

    private Long id;
    private String title;
    private String description;
    private String url;

    public GalleryAddServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public GalleryAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GalleryAddServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GalleryAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public GalleryAddServiceModel setUrl(String url) {
        this.url = url;
        return this;
    }
}
