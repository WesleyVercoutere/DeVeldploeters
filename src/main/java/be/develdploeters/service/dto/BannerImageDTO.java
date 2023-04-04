package be.develdploeters.service.dto;

import lombok.Data;

import javax.persistence.Lob;
import java.io.Serializable;

/**
 * A DTO for the {@link be.develdploeters.domain.BannerImage} entity.
 */
@Data
public class BannerImageDTO implements Serializable {

    private Long id;
    private Boolean active;

    @Lob
    private byte[] image;
    private String imageContentType;
    public Boolean isActive() {
        return active;
    }

}
