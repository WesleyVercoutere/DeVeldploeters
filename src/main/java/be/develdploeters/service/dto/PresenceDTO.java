package be.develdploeters.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link be.develdploeters.domain.Presence} entity.
 */
@Data
public class PresenceDTO implements Serializable {

    private Long id;
    private boolean present;
    private Long activityId;
    private Long userId;

    private String username;

}
