package be.develdploeters.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Organization.
 */
@Data
@Entity
@Table(name = "organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "website")
    private String website;

    @Column(name = "email")
    private String email;

}
