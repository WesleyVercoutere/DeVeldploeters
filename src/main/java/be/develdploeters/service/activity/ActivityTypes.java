package be.develdploeters.service.activity;

import be.develdploeters.service.activity.impl.EventService;
import be.develdploeters.service.activity.impl.TourService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActivityTypes {

    private Map<String, IActivityService> map;

    private EventService eventService;
    private TourService tourService;

    public ActivityTypes(EventService eventService, TourService tourService) {
        this.map = new HashMap<>();
        this.eventService = eventService;
        this.tourService = tourService;

        initMap();
    }

    private void initMap() {
        map.put("event", eventService);
        map.put("tour", tourService);
    }

    public IActivityService getService(String key) {
        return map.get(key);
    }

}
