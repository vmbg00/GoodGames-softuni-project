package bg.softuni.gamingstore.models.services;

import bg.softuni.gamingstore.models.entities.enums.RoleEnums;

public class ChangeUserRoleServiceModel {
    private String username;
    private RoleEnums role;

    public ChangeUserRoleServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public ChangeUserRoleServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public RoleEnums getRole() {
        return role;
    }

    public ChangeUserRoleServiceModel setRole(RoleEnums role) {
        this.role = role;
        return this;
    }
}
