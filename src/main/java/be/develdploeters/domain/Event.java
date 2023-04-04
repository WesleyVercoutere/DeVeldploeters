package be.develdploeters.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * A Event.
 */
@Data
@Entity
@DiscriminatorValue("event")
public class Event extends Activity {

    @Column(name = "end_date")
    private LocalDate endDate;

}
