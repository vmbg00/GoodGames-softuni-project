package bg.softuni.gamingstore.models.services;

public class DemoteUserServiceModel {
    private String username;

    public DemoteUserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public DemoteUserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
