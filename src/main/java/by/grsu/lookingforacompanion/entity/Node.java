package by.grsu.lookingforacompanion.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "nodes")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "required_people")
    private Integer requiredPeople;

    @Column(name = "age_limit")
    private Integer ageLimit;

    @Column(name = "publish_time")
    private Date publishTime;

    @Column(name = "expiration_time")
    private Date expirationTime;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User owner;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_categories_id")
    private SubCategory subCategory;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "node")
    private List<Comment> comments;

    @ToString.Exclude
    @ManyToMany(mappedBy = "joinedEvents",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private List<User> joinedUsers;

}
