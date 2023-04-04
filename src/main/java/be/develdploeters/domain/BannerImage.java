package be.develdploeters.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A BannerImage.
 */
@Data
@Entity
@Table(name = "banner_image")
public class BannerImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active")
    private Boolean active;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    public Boolean isActive() {
        return active;
    }

    public BannerImage active(Boolean active) {
        this.active = active;
        return this;
    }

}
