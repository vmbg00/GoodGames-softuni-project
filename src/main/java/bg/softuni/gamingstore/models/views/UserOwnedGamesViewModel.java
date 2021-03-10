package bg.softuni.gamingstore.models.views;

public class UserOwnedGamesViewModel {
    private String title;
    private String platform;
    private String genre;
    private String boughtFor;

    public UserOwnedGamesViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public UserOwnedGamesViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public UserOwnedGamesViewModel setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public UserOwnedGamesViewModel setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getBoughtFor() {
        return boughtFor;
    }

    public UserOwnedGamesViewModel setBoughtFor(String boughtFor) {
        this.boughtFor = boughtFor;
        return this;
    }
}
