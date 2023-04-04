package be.develdploeters.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link be.develdploeters.domain.Address} entity.
 */
@Data
public class AddressDTO implements Serializable {

    private Long id;
    private String street;
    private String number;
    private String zipCode;
    private String city;

}
