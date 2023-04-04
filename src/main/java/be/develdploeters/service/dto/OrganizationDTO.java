package be.develdploeters.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link be.develdploeters.domain.Organization} entity.
 */
@Data
public class OrganizationDTO implements Serializable {

    private Long id;
    private String name;
    private String website;
    private String email;

}
