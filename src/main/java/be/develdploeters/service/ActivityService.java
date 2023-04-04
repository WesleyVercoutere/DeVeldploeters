package be.develdploeters.service;

import be.develdploeters.domain.Activity;
import be.develdploeters.domain.Event;
import be.develdploeters.repository.ActivityRepository;
import be.develdploeters.service.activity.ActivityContext;
import be.develdploeters.service.activity.impl.EventService;
import be.develdploeters.service.activity.impl.TourService;
import be.develdploeters.service.dto.ActivityDTO;
import be.develdploeters.service.mapper.ActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Activity}.
 */
@Service
@Transactional
public class ActivityService {

    private final Logger log = LoggerFactory.getLogger(ActivityService.class);
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    private final ActivityContext context;

    public ActivityService(ActivityRepository activityRepository,
                           ActivityMapper activityMapper,
                           ActivityContext activityContext) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
        this.context = activityContext;
    }

    /**
     * Save a activity.
     *
     * @param activityDTO the entity to save.
     * @return the persisted entity.
     */
    public ActivityDTO save(ActivityDTO activityDTO) {
        log.debug("Request to save Activity : {}", activityDTO);
        return context.save(activityDTO);
    }

    public Activity save(Activity activity) {
        log.debug("Request to save Activity : {}", activity);
        return activityRepository.save(activity);
    }

    /**
     * Save a activity.
     *
     * @param activityDTO the entity to save.
     * @return the persisted entity.
     */
    public ActivityDTO create(ActivityDTO activityDTO) {
        log.debug("Request to save Activity : {}", activityDTO);
        return context.create(activityDTO);
    }

    /**
     * Get all the activities.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ActivityDTO> findAll() {
        log.debug("Request to get all Activities");
        return activityRepository.findAll().stream()
            .map(activityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public List<Activity> findAllActivities() {
        log.debug("Request to get all Activities");
        return activityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Activity findOne(Long id) {
        return activityRepository.getOne(id);
    }

    @Transactional(readOnly = true)
    public ActivityDTO findById(Long id) {
        log.debug("Request to get Activity : {}", id);
        Activity activity = activityRepository.getOne(id);
        return context.find(activity);
    }

    /**
     * Delete the activity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Activity : {}", id);
        activityRepository.deleteById(id);
    }

    public List<Activity> findAllFromYear(int year) {
        return activityRepository.findActivitiesByYear(year);
    }

    public List<ActivityDTO> findAllDtoFromYear(Integer year) {
        return activityMapper.toDto(activityRepository.findActivitiesByYear(year));
    }

    public List<Activity> findLastActivitiesWithReport() {
        List<Activity> activities = activityRepository.findActivitiesFetchedReport().stream().limit(5L).collect(Collectors.toList());
        return activities.stream().filter(e -> e.getReport() != null).limit(5L).collect(Collectors.toList());
    }
}
