package bg.softuni.gamingstore.models.entities;

import bg.softuni.gamingstore.models.entities.enums.RoleEnums;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    private RoleEnums name;
    private String description;

    public RoleEntity() {
    }

    public RoleEnums getName() {
        return name;
    }

    public RoleEntity setName(RoleEnums name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoleEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
