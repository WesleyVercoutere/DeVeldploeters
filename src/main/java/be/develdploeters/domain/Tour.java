package be.develdploeters.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * A Tour.
 */
@Data
@Entity
@DiscriminatorValue("tour")
public class Tour extends Activity {

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "distance")
    private String distance;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("tours")
    private Organization organization;

}
