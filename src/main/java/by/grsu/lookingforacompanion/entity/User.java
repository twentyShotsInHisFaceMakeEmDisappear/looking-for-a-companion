package by.grsu.lookingforacompanion.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "displayed_name")
    private String displayedName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "credentials_id")
    private Credential credentials;

    @ToString.Exclude
    @ManyToMany(mappedBy = "subscribers",
        fetch = FetchType.LAZY)
    private List<Category> likedCategories;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "owner")
    private List<Node> nodes;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "author")
    private List<Comment> comments;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinTable(name = "users_nodes",
        joinColumns = @JoinColumn(name = "users_id"),
        inverseJoinColumns = @JoinColumn(name = "nodes_id"))
    private List<Node> joinedEvents;

}
