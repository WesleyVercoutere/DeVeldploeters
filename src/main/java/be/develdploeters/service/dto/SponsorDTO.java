package be.develdploeters.service.dto;

import lombok.Data;

import javax.persistence.Lob;
import java.io.Serializable;

/**
 * A DTO for the {@link be.develdploeters.domain.Sponsor} entity.
 */
@Data
public class SponsorDTO implements Serializable {

    private Long id;
    private String name;
    private String website;

    @Lob
    private byte[] websiteImage;
    private String websiteImageContentType;
    @Lob
    private byte[] logo;
    private String logoContentType;

}
