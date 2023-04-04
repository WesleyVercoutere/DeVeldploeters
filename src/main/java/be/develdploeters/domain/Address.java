package be.develdploeters.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Address.
 */
@Data
@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "jhi_number")
    private String number;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

}
