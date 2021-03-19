package bg.softuni.gamingstore.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity{

    private String url;
    private String title;
    private String description;
    private UserEntity userEntity;

    public PictureEntity() {
    }

    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public PictureEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Column
    public String getUrl() {
        return url;
    }

    public PictureEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    @Column(unique = true, nullable = false)
    public String getTitle() {
        return title;
    }

    public PictureEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public PictureEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
