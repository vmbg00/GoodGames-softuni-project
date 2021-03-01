package bg.softuni.gamingstore.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "forum_posts")
public class ForumPosts extends BaseEntity{

    private String title;
    private LocalDateTime published;

    public ForumPosts() {
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column
    public LocalDateTime getPublished() {
        return published;
    }

    public ForumPosts setPublished(LocalDateTime published) {
        this.published = published;
        return this;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public ForumPosts setTitle(String title) {
        this.title = title;
        return this;
    }
}
