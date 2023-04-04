package be.develdploeters.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link be.develdploeters.domain.ICE} entity.
 */
@Data
public class ICEDTO implements Serializable {

    private Long id;
    private String lastName;
    private String firstName;
    private String phone;
    private Long userId;

}
