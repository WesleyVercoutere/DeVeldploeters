package be.develdploeters.service;

import be.develdploeters.domain.Activity;
import be.develdploeters.domain.Event;
import be.develdploeters.service.dto.CalendarDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CalendarService {

    private ActivityService activityService;

    public CalendarService(ActivityService activityService) {
        this.activityService = activityService;
    }

    public List<CalendarDTO> findAll() {

        List<Activity> activities = activityService.findAllActivities();

        return activities.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private CalendarDTO mapToDTO(Activity activity) {
        CalendarDTO dto = new CalendarDTO();

        dto.setId(activity.getId());
        dto.setType(activity.getType());
        dto.setTitle(activity.getTitle());
        dto.setStart(activity.getDate().toString());

        if(activity.getType()!= null && activity.getType().equals("event")) {
            dto.setEnd( ( ((Event)activity).getEndDate() == null) ? activity.getDate().toString() : ((Event)activity).getEndDate().toString());
        } else {
            dto.setEnd(activity.getDate().toString());
        }


        return dto;
    }
}
