package bg.softuni.gamingstore.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "forum_posts")
public class ForumPosts extends BaseEntity{

    private String title;

    public ForumPosts() {
    }

    public String getTitle() {
        return title;
    }

    public ForumPosts setTitle(String title) {
        this.title = title;
        return this;
    }
}
