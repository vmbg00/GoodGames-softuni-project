package bg.softuni.gamingstore.models.services;

import org.springframework.web.multipart.MultipartFile;

public class GalleryAddServiceModel {

    private Long id;
    private String title;
    private String description;
    private MultipartFile img;

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

    public MultipartFile getImg() {
        return img;
    }

    public GalleryAddServiceModel setUrl(MultipartFile img) {
        this.img = img;
        return this;
    }
}
