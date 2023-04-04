package be.develdploeters.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link be.develdploeters.domain.Report} entity.
 */
@Data
public class ReportDTO implements Serializable {

    private Long id;
    private String report;

}
