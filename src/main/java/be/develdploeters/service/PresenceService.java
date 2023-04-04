package be.develdploeters.service;

import be.develdploeters.domain.Activity;
import be.develdploeters.domain.Presence;
import be.develdploeters.domain.User;
import be.develdploeters.repository.PresenceRepository;
import be.develdploeters.service.dto.PresenceDTO;
import be.develdploeters.service.dto.PresenceOverviewDTO;
import be.develdploeters.service.mapper.PresenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Presence}.
 */
@Service
@Transactional
public class PresenceService {

    private final Logger log = LoggerFactory.getLogger(PresenceService.class);

    private final PresenceRepository presenceRepository;

    private final PresenceMapper presenceMapper;
    private final UserService userService;
    private final ActivityService activityService;

    public PresenceService(PresenceRepository presenceRepository,
                           PresenceMapper presenceMapper,
                           UserService userService,
                           @Lazy ActivityService activityService) {
        this.presenceRepository = presenceRepository;
        this.presenceMapper = presenceMapper;
        this.userService = userService;
        this.activityService = activityService;
    }

    /**
     * Save a presence.
     *
     * @param presenceDTO the entity to save.
     * @return the persisted entity.
     */
    public PresenceDTO save(PresenceDTO presenceDTO) {
        log.debug("Request to save Presence : {}", presenceDTO);
        Presence presence = presenceRepository.findByIdFetched(presenceDTO.getId());
        presence.setPresent(presenceDTO.isPresent());
        Presence savedPresence = presenceRepository.save(presence);
        return presenceMapper.toDto(savedPresence);
    }

    /**
     * Save a presence.
     *
     * @param presenceDTO the entity to save.
     * @return the persisted entity.
     */
    public PresenceDTO create(PresenceDTO presenceDTO) {
        log.debug("Request to save Presence : {}", presenceDTO);
        Presence presence = new Presence();

        Optional<Presence> present = presenceRepository.findByActivityIdAndUserId(presenceDTO.getActivityId(), presenceDTO.getUserId());

        if (present.isPresent()) {
            return null;
        }

        presence.setActivity(activityService.findOne(presenceDTO.getActivityId()));
        presence.setUser(userService.getUserById(presenceDTO.getUserId()));
        presence = presenceRepository.save(presence);
        return presenceMapper.toDto(presence);
    }

    /**
     * Get all the presences.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PresenceDTO> findAll() {
        log.debug("Request to get all Presences");
        return presenceRepository.findAll().stream()
            .map(presenceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one presence by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PresenceDTO> findOne(Long id) {
        log.debug("Request to get Presence : {}", id);
        return presenceRepository.findById(id)
            .map(presenceMapper::toDto);
    }

    /**
     * Get one presence by activity and user id.
     *
     * @param id the id of the activity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public PresenceDTO findCurrentUserActivityId(Long id) {
        log.debug("Request to get Presence : {}", id);

        Optional<Presence> presence = presenceRepository.findByActivityIdAndUserId(id, userService.getCurrentUserId());

        if (presence.isPresent()) {
            return presenceMapper.toDto(presence.get());
        } else {
            return createNewPresence(id);
        }
    }

    private PresenceDTO createNewPresence(Long activityId) {
        Presence presence = new Presence();
        presence.setPresent(false);

        Activity activity = activityService.findOne(activityId);
        LocalDate now = LocalDate.now();

        if (now.isAfter(activity.getDate())) {
            return null;
        }

        presence.setActivity(activity);
        presence.setUser(userService.getCurrentUser());

        presenceRepository.save(presence);

        return presenceMapper.toDto(presence);
    }

    /**
     * Delete the presence by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Presence : {}", id);
        presenceRepository.deleteById(id);
    }

    public List<PresenceDTO> findActivityId(Long id) {
        return presenceRepository.findByActivityIdFetched(id)
            .stream()
            .map(presenceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public void createPresences(Activity activity) {
        List<User> activeUsers = userService.getActiveUsers();
        activeUsers.forEach(user -> createPresence(activity, user));
    }

    public void createPresence(Activity activity, User user) {
        Presence presence = new Presence();
        presence.setPresent(false);
        presence.setUser(user);
        presence.setActivity(activity);

        presenceRepository.save(presence);
    }

    public List<PresenceOverviewDTO> findOverviewOfYear(int year) {
        List<PresenceOverviewDTO> dtos = new ArrayList<>();
        List<Presence> presences = presenceRepository.findByYearFetched(year);
        List<Activity> activities = activityService.findAllFromYear(year);
        List<User> users = presences.stream().map(Presence::getUser).distinct().collect(Collectors.toList());

        for (User user : users) {
            PresenceOverviewDTO dto = new PresenceOverviewDTO();
            dto.setUser(user.getFirstName() + " " + user.getLastName().substring(0, 1));

            int total = 0;

            for (Activity activity : activities) {
                boolean present = false;

                for (Presence presence : presences) {
                    if (activity.getId() == presence.getActivity().getId() && user.getId() == presence.getUser().getId()) {
                        present = presence.isPresent();

                        if (present) {
                            total++;
                        }
                    }
                }

                dto.getPresences().add(present);
            }

            dto.setQtyPresences(total);
            dtos.add(dto);
        }

        return dtos;
    }


}
