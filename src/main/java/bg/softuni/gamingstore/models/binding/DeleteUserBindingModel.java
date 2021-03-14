package bg.softuni.gamingstore.models.binding;

import javax.validation.constraints.NotNull;

public class DeleteUserBindingModel {
    private String username;

    public DeleteUserBindingModel() {
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public DeleteUserBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
