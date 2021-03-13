package bg.softuni.gamingstore.models.binding;

import bg.softuni.gamingstore.models.entities.enums.RoleEnums;

import javax.validation.constraints.NotNull;

public class ChangeUserRoleBindingModel {
    private String username;
    private RoleEnums role;

    public ChangeUserRoleBindingModel() {
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public ChangeUserRoleBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public RoleEnums getRole() {
        return role;
    }

    public ChangeUserRoleBindingModel setRole(RoleEnums role) {
        this.role = role;
        return this;
    }
}
