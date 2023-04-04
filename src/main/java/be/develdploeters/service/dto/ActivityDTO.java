package be.develdploeters.service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ActivityDTO {

    private Long id;
    private String type;
    private String title;
    private LocalDate date;
    private LocalDate endDate;
    private LocalTime time;
    private LocalTime startTime;
    private String distance;

    private String location;
    private Long addressId;
    private String street;
    private String number;
    private String zip;
    private String city;

    private Long organisationId;
    private String name;
    private String website;
    private String email;

}
