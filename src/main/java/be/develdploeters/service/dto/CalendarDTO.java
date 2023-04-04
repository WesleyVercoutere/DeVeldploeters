package be.develdploeters.service.dto;

import lombok.Data;

@Data
public class CalendarDTO {

    public Long id;
    public String type;
    public String title;
    public String start;
    public String end;

}
