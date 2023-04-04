package be.develdploeters.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Sponsor.
 */
@Data
@Entity
@Table(name = "sponsor")
public class Sponsor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "website")
    private String website;

    @Lob
    @Column(name = "website_image")
    private byte[] websiteImage;

    @Column(name = "website_image_content_type")
    private String websiteImageContentType;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_content_type")
    private String logoContentType;

}
