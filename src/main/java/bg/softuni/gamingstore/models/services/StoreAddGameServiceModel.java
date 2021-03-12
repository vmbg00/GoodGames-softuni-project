package bg.softuni.gamingstore.models.services;

import bg.softuni.gamingstore.models.entities.enums.GenreEnum;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class StoreAddGameServiceModel {
    private String name;
    private String description;
    private BigDecimal price;
    private String platform;
    private GenreEnum genre;
    private MultipartFile imageUrl;

    public StoreAddGameServiceModel() {
    }

    public String getName() {
        return name;
    }

    public StoreAddGameServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StoreAddGameServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public StoreAddGameServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public StoreAddGameServiceModel setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public StoreAddGameServiceModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public StoreAddGameServiceModel setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
