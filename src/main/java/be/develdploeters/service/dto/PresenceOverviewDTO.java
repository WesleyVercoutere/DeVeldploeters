package be.develdploeters.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PresenceOverviewDTO {

    private String user;
    private int qtyPresences;
    private List<Boolean> presences;

    public PresenceOverviewDTO() {
        presences = new ArrayList<>();
    }
}
