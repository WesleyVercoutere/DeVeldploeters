package be.develdploeters.web.rest;

import be.develdploeters.service.PresenceService;
import be.develdploeters.service.dto.PresenceDTO;
import be.develdploeters.service.dto.PresenceOverviewDTO;
import be.develdploeters.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link be.develdploeters.domain.Presence}.
 */
@RestController
@RequestMapping("/api")
public class PresenceResource {

    private final Logger log = LoggerFactory.getLogger(PresenceResource.class);

    private static final String ENTITY_NAME = "presence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PresenceService presenceService;

    public PresenceResource(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    /**
     * {@code POST  /presences} : Create a new presence.
     *
     * @param presenceDTO the presenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new presenceDTO, or with status {@code 400 (Bad Request)} if the presence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/presences")
    public ResponseEntity<PresenceDTO> createPresence(@RequestBody PresenceDTO presenceDTO) throws URISyntaxException {
        log.debug("REST request to save Presence : {}", presenceDTO);
        if (presenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new presence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PresenceDTO result = presenceService.create(presenceDTO);

        if (result == null)
            ResponseEntity.status(HttpStatus.CONFLICT);

        return ResponseEntity.created(new URI("/api/presences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /presences} : Updates an existing presence.
     *
     * @param presenceDTO the presenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated presenceDTO,
     * or with status {@code 400 (Bad Request)} if the presenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the presenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/presences")
    public ResponseEntity<PresenceDTO> updatePresence(@RequestBody PresenceDTO presenceDTO) throws URISyntaxException {
        log.debug("REST request to update Presence : {}", presenceDTO);
        if (presenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PresenceDTO result = presenceService.save(presenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, presenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /presences} : get all the presences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of presences in body.
     */
    @GetMapping("/presences")
    public List<PresenceDTO> getAllPresences() {
        log.debug("REST request to get all Presences");
        return presenceService.findAll();
    }

    /**
     * {@code GET  /presencesActivity/:id} : get the presences from an activity.
     *
     * @param id the id of the activity presenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the presenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/presencesActivity/{id}")
    public List<PresenceDTO> getPresencesActivityId(@PathVariable Long id) {
        log.debug("REST request to get Presence : {}", id);
        return presenceService.findActivityId(id);
    }


    /**
     * {@code GET  /presencesOverview/:year} : get the presences from an year.
     *
     * @param year the year of activities presenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the presenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/presencesOverview/{year}")
    public List<PresenceOverviewDTO> getPresencesOverview(@PathVariable Integer year) {
        log.debug("REST request to get Presence : {}", year);
        return presenceService.findOverviewOfYear(year);
    }

    /**
     * {@code GET  /presences/:id} : get the "id" presence.
     *
     * @param id the id of the presenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the presenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/presences/{id}")
    public ResponseEntity<PresenceDTO> getPresence(@PathVariable Long id) {
        log.debug("REST request to get Presence : {}", id);
        Optional<PresenceDTO> presenceDTO = presenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(presenceDTO);
    }

    /**
     * {@code GET  /presencesUserActivity/:id} : get the "activityId" presence.
     *
     * @param id the id of the activity presenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the presenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/presencesUserActivity/{id}")
    public ResponseEntity<PresenceDTO> getPresenceFromCurrentUserActivityId(@PathVariable Long id) {
        log.debug("REST request to get Presence : {}", id);
        PresenceDTO presenceDTO = presenceService.findCurrentUserActivityId(id);

        if (presenceDTO == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .body(presenceDTO);
    }

    /**
     * {@code DELETE  /presences/:id} : delete the "id" presence.
     *
     * @param id the id of the presenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/presences/{id}")
    public ResponseEntity<Void> deletePresence(@PathVariable Long id) {
        log.debug("REST request to delete Presence : {}", id);
        presenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
