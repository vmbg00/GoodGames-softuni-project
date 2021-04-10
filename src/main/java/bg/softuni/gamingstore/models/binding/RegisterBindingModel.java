package bg.softuni.gamingstore.models.binding;

import bg.softuni.gamingstore.models.validators.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword"
)
public class RegisterBindingModel {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterBindingModel() {
    }

    @NotBlank(message = "Cannot be blank")
    @Size(min = 4, message = "Must be minimum 4 characters")
    public String getUsername() {
        return username;
    }

    public RegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public RegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotBlank(message = "Cannot be blank")
    @Size(min = 5, message = "Must be minimum 5 characters")
    public String getPassword() {
        return password;
    }

    public RegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank(message = "Cannot be blank")
    @Size(min = 5, message = "Must be minimum 5 characters")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
