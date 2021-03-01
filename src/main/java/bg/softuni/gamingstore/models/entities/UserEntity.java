package bg.softuni.gamingstore.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    private String username;
    private String password;
    private String email;
    private List<RoleEntity> roles;
    private List<GameEntity> games;
    private List<ForumPosts> posts;
    private List<BlogEntity> blogs;

    public UserEntity() {
    }

    @Column(unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @ManyToMany
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    @ManyToMany
    public List<BlogEntity> getBlogs() {
        return blogs;
    }

    public UserEntity setBlogs(List<BlogEntity> blogs) {
        this.blogs = blogs;
        return this;
    }

    @ManyToMany
    public List<GameEntity> getGames() {
        return games;
    }

    public UserEntity setGames(List<GameEntity> games) {
        this.games = games;
        return this;
    }

    @ManyToMany
    public List<ForumPosts> getPosts() {
        return posts;
    }

    public UserEntity setPosts(List<ForumPosts> posts) {
        this.posts = posts;
        return this;
    }
}
