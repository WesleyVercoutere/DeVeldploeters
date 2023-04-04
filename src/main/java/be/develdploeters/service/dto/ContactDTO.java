package be.develdploeters.service.dto;

import lombok.Data;

@Data
public class ContactDTO {

    private String lastName;
    private String firstName;
    private String email;
    private String text;
    private String street;
    private int number;
    private int zip;
    private String city;
}
