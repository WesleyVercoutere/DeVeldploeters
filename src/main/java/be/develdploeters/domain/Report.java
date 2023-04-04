package be.develdploeters.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Report.
 */
@Data
@Entity
@Table(name = "report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="report", columnDefinition="TEXT")
    private String report;

}
