package bg.softuni.gamingstore.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blogs")
public class BlogEntity extends BaseEntity{

    private String title;

    public BlogEntity() {
    }

    public String getTitle() {
        return title;
    }

    public BlogEntity setTitle(String title) {
        this.title = title;
        return this;
    }
}
