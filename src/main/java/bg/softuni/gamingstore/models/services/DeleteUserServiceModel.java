package bg.softuni.gamingstore.models.services;

public class DeleteUserServiceModel {
    private String username;

    public DeleteUserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public DeleteUserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
