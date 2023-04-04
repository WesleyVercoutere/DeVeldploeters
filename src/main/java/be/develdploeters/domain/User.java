package be.develdploeters.domain;

import be.develdploeters.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A user.
 */
@Data
@Entity
@Table(name = "jhi_user")
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private String password;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private boolean activated = false;

    @Size(min = 2, max = 6)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Column(name = "lang_key", length = 6)
    private String langKey;

    @Size(max = 256)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private String resetKey;

    @Column(name = "reset_date")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Instant resetDate = null;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})

    @BatchSize(size = 20)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Authority> authorities = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PersistentToken> persistentTokens = new HashSet<>();

    @Column(name = "phone")
    private String phone;

    @OneToOne
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ICE> ices = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Presence> activities = new HashSet<>();

    public User addActivities(Presence presence) {
        this.activities.add(presence);
        presence.setUser(this);
        return this;
    }

    public User removeActivities(Presence presence) {
        this.activities.remove(presence);
        presence.setUser(null);
        return this;
    }

}
