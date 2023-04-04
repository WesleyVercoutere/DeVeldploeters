package be.develdploeters.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportOverviewDTO {

    private LocalDate date;
    private String title;
    private String report;

}
