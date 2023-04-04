package be.develdploeters.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A DTO for the {@link be.develdploeters.domain.Event} entity.
 */
@Data
public class EventDTO implements Serializable {

    private Long id;
    private String type;
    private String title;
    private LocalDate date;
    private LocalDate endDate;
    private LocalTime time;

    private String location;
    private Long addressId;
    private String street;
    private String number;
    private int zip;
    private String city;

}
