package be.develdploeters.web.rest;

import be.develdploeters.security.AuthoritiesConstants;
import be.develdploeters.service.ActivityService;
import be.develdploeters.service.dto.ActivityDTO;
import be.develdploeters.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link be.develdploeters.domain.Activity}.
 */
@RestController
@RequestMapping("/api")
public class ActivityResource {

    private final Logger log = LoggerFactory.getLogger(ActivityResource.class);

    private static final String ENTITY_NAME = "activity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActivityService activityService;

    public ActivityResource(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * {@code POST  /activities} : Create a new activity.
     *
     * @param activityDTO the activityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activityDTO, or with status {@code 400 (Bad Request)} if the activity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/activities")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasRole(\"" + AuthoritiesConstants.BOARD  + "\")")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) throws URISyntaxException {
        log.debug("REST request to save Activity : {}", activityDTO);
        if (activityDTO.getId() != null) {
            throw new BadRequestAlertException("A new activity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActivityDTO result = activityService.create(activityDTO);
        return ResponseEntity.created(new URI("/api/activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /activities} : Updates an existing activity.
     *
     * @param activityDTO the activityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activityDTO,
     * or with status {@code 400 (Bad Request)} if the activityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/activities")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasRole(\"" + AuthoritiesConstants.BOARD  + "\")")
    public ResponseEntity<ActivityDTO> updateActivity(@RequestBody ActivityDTO activityDTO) throws URISyntaxException {
        log.debug("REST request to update Activity : {}", activityDTO);
        if (activityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActivityDTO result = activityService.save(activityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, activityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /activities} : get all the activities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activities in body.
     */
    @GetMapping("/activities")
    public List<ActivityDTO> getAllActivities() {
        log.debug("REST request to get all Activities");
        return activityService.findAll();
    }

    /**
     * {@code GET  /activitiesByYear/:year} : get all the activities of a selected year.
     *
     * @param year the year of the activityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activities in body.
     */
    @GetMapping("/activitiesByYear/{year}")
    public List<ActivityDTO> getAllActivitiesFromYear(@PathVariable Integer year) {
        log.debug("REST request to get all Activities");
        return activityService.findAllDtoFromYear(year);
    }

    /**
     * {@code GET  /activities/:id} : get the "id" activity.
     *
     * @param id the id of the activityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/activities/{id}")
    public ResponseEntity<ActivityDTO> getActivity(@PathVariable Long id) {
        log.debug("REST request to get Activity : {}", id);
        ActivityDTO activityDTO = activityService.findById(id);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .body(activityDTO);
    }

    /**
     * {@code DELETE  /activities/:id} : delete the "id" activity.
     *
     * @param id the id of the activityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/activities/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasRole(\"" + AuthoritiesConstants.BOARD  + "\")")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        log.debug("REST request to delete Activity : {}", id);
        activityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
