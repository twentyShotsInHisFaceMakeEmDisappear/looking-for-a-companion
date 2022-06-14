package by.grsu.lookingforacompanion.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "image_url")
    private String imageUrl;

    @ToString.Exclude
    @OneToMany(mappedBy = "categoryOwner")
    private List<SubCategory> subCategories;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinTable(name = "users_categories",
        joinColumns = @JoinColumn(name = "categories_id"),
        inverseJoinColumns = @JoinColumn(name = "users_id"))
    private List<User> subscribers;

}
