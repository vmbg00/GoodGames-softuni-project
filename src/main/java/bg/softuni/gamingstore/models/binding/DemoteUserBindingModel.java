package bg.softuni.gamingstore.models.binding;

import javax.validation.constraints.NotNull;

public class DemoteUserBindingModel {
    private String username;

    public DemoteUserBindingModel() {
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public DemoteUserBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
