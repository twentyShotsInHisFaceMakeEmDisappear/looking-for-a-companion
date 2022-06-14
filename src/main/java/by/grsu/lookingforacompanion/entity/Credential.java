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
@Table(name = "credentials")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ToString.Exclude
    @OneToOne(mappedBy = "credentials",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private User user;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "credentials")
    private List<Role> roles;

}
