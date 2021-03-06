package bg.softuni.gamingstore.models.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class StoreAddGameBindingModel {
    private String name;
    private String description;
    private BigDecimal price;
    private String platform;
    private String genre;
    private MultipartFile imageUrl;

    public StoreAddGameBindingModel() {
    }

    @NotBlank(message = "Must be filled")
    @Size(min = 4, message = "Minimum 4 characters long")
    public String getName() {
        return name;
    }

    public StoreAddGameBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank(message = "Must be filled")
    @Size(min = 15, message = "Minimum 15 characters long")
    public String getDescription() {
        return description;
    }

    public StoreAddGameBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @DecimalMin(value = "0", message = "Game price must be a positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public StoreAddGameBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotBlank(message = "Must select a platform")
    public String getPlatform() {
        return platform;
    }

    public StoreAddGameBindingModel setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    @NotBlank(message = "Must select a genre")
    public String getGenre() {
        return genre;
    }

    public StoreAddGameBindingModel setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    @NotNull
    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public StoreAddGameBindingModel setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
