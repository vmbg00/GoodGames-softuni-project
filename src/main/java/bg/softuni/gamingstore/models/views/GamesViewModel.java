package bg.softuni.gamingstore.models.views;

public class GamesViewModel {
    private String imageUrl;
    private String name;
    private String price;

    public GamesViewModel() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public GamesViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public GamesViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public GamesViewModel setPrice(String price) {
        this.price = price;
        return this;
    }
}
