package be.develdploeters.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A DTO for the {@link be.develdploeters.domain.Tour} entity.
 */
@Data
public class TourDTO implements Serializable {

    private Long id;
    private String type;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private LocalTime startTime;
    private String distance;

    private Long organisationId;
    private String name;
    private String website;
    private String email;

    private String location;
    private Long addressId;
    private String street;
    private String number;
    private int zip;
    private String city;

}
