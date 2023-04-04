package be.develdploeters.service.activity;

import be.develdploeters.domain.Activity;
import be.develdploeters.repository.ActivityRepository;
import be.develdploeters.service.PresenceService;
import be.develdploeters.service.dto.ActivityDTO;
import org.springframework.stereotype.Service;

@Service
public class ActivityContext {

    private final ActivityRepository activityRepository;
    private final ActivityTypes activityTypes;
    private final PresenceService presenceService;

    private IActivityService service;

    public ActivityContext(ActivityRepository activityRepository,
                           ActivityTypes activityTypes,
                           PresenceService presenceService) {
        this.activityRepository = activityRepository;
        this.activityTypes = activityTypes;
        this.presenceService = presenceService;
    }

    private void setContext(String type) {
        service = activityTypes.getService(type);
    }

    public ActivityDTO find(Activity activity) {
        setContext(activity.getType());
        return service.findById(activity.getId());
    }

    public ActivityDTO save(ActivityDTO activityDTO) {
        setContext(activityDTO.getType());
        return service.save(activityDTO);
    }

    public ActivityDTO create(ActivityDTO activityDTO) {
        setContext(activityDTO.getType());
        ActivityDTO dto = service.save(activityDTO);

        Activity activity = activityRepository.getOne(dto.getId());
        presenceService.createPresences(activity);

        return dto;
    }
}
