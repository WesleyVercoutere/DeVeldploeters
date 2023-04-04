package be.develdploeters.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Activity.
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "activity", discriminatorType = DiscriminatorType.STRING)
public abstract class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="activity", insertable = false, updatable = false)
    protected String type;

    @Column(name = "title")
    private String title;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "jhi_time")
    private LocalTime time;

    @Column(name = "location")
    private String location;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Report report;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Presence> attendances = new HashSet<>();

    public Activity addAttendances(Presence presence) {
        this.attendances.add(presence);
        presence.setActivity(this);
        return this;
    }

    public Activity removeAttendances(Presence presence) {
        this.attendances.remove(presence);
        presence.setActivity(null);
        return this;
    }

}
