package vn.iotstar.entity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "categories")
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(nullable = false, length = 100)
private String name;


@Column(length = 255)
private String images; // URL ảnh


// Many-to-Many với User
@ManyToMany(mappedBy = "categories")
private Set<User> users = new HashSet<>();


// getters/setters
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getImages() { return images; }
public void setImages(String images) { this.images = images; }
public Set<User> getUsers() { return users; }
public void setUsers(Set<User> users) { this.users = users; }
}