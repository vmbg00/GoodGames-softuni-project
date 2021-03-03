package bg.softuni.gamingstore.models.views;

public class GalleryViewModel {
    private String url;
    private String title;
    private String description;

    public GalleryViewModel() {
    }

    public String getUrl() {
        return url;
    }

    public GalleryViewModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GalleryViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GalleryViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
