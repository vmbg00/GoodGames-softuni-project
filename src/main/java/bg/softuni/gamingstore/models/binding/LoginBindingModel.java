package bg.softuni.gamingstore.models.binding;

import javax.validation.constraints.NotBlank;

public class LoginBindingModel {
    private String username;
    private String password;

    public LoginBindingModel() {
    }

    @NotBlank(message = "Cannot be blank")
    public String getUsername() {
        return username;
    }

    public LoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank(message = "Cannot be blank")
    public String getPassword() {
        return password;
    }

    public LoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
