package bg.softuni.gamingstore.models.views;

import java.math.BigDecimal;

public class ShoppingCartGamesViewModel {
    private String imageUrl;
    private String name;
    private BigDecimal price;

    public ShoppingCartGamesViewModel() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoppingCartGamesViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoppingCartGamesViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoppingCartGamesViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
