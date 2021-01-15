package finalproject.jw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finalproject.jw.dto.UserRegistrationDTO;
import finalproject.jw.exception.UserException;
import finalproject.jw.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="user_name")
    private String name;

    @Column(name = "is_admin")
    @Type(type="boolean")
    private boolean isAdmin;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(targetEntity = Credit.class)
    @JoinTable(name = "user_credit_card",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "credit_card_id", referencedColumnName = "id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {
                            "user_id",
                            "credit_card_id"
                    })
            })
    private Set<Credit> creditCards;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private Set<Seller> sellers;

    @JsonIgnore
    @OneToOne
    @EqualsAndHashCode.Exclude
    private Cart cart;

    public User(UserRegistrationDTO user) throws UserException {
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = UserService.stringToSha1(user.getPassword());
        this.setAdmin(true);
    }
}
