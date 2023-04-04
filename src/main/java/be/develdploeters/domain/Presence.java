package be.develdploeters.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Presence.
 */
@Data
@Entity
@Table(name = "presence")
public class Presence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "present")
    private boolean present;

    @ManyToOne
    @JsonIgnoreProperties("attendances")
    private Activity activity;

    @ManyToOne
    @JsonIgnoreProperties("activities")
    private User user;

}
