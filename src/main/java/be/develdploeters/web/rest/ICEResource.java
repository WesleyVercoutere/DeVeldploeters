package be.develdploeters.web.rest;

import be.develdploeters.service.ICEService;
import be.develdploeters.service.dto.ICEDTO;
import be.develdploeters.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link be.develdploeters.domain.ICE}.
 */
@RestController
@RequestMapping("/api")
public class ICEResource {

    private final Logger log = LoggerFactory.getLogger(ICEResource.class);

    private static final String ENTITY_NAME = "iCE";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ICEService iCEService;

    public ICEResource(ICEService iCEService) {
        this.iCEService = iCEService;
    }

    /**
     * {@code POST  /ices} : Create a new iCE.
     *
     * @param iCEDTO the iCEDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new iCEDTO, or with status {@code 400 (Bad Request)} if the iCE has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ices")
    public ResponseEntity<ICEDTO> createICE(@RequestBody ICEDTO iCEDTO) throws URISyntaxException {
        log.debug("REST request to save ICE : {}", iCEDTO);
        if (iCEDTO.getId() != null) {
            throw new BadRequestAlertException("A new iCE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ICEDTO result = iCEService.create(iCEDTO);
        return ResponseEntity.created(new URI("/api/ices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ices} : Updates an existing iCE.
     *
     * @param iCEDTO the iCEDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated iCEDTO,
     * or with status {@code 400 (Bad Request)} if the iCEDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the iCEDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ices")
    public ResponseEntity<ICEDTO> updateICE(@RequestBody ICEDTO iCEDTO) throws URISyntaxException {
        log.debug("REST request to update ICE : {}", iCEDTO);
        if (iCEDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ICEDTO result = iCEService.save(iCEDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, iCEDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ices} : get all the iCES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of iCES in body.
     */
    @GetMapping("/ices")
    public List<ICEDTO> getAllICES() {
        log.debug("REST request to get all ICES");
        return iCEService.findAll();
    }

    /**
     * GET  /ices : get the iCES from the current logged in user.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of iCES in body
     */
    @GetMapping("/userIces")
    public List<ICEDTO> getCurrentUserICES() {
        log.debug("REST request to get all ICES for the current logged in user");
        return iCEService.findAllCurrentUser();
    }

    /**
     * {@code GET  /ices/:id} : get the "id" iCE.
     *
     * @param id the id of the iCEDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the iCEDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ices/{id}")
    public ResponseEntity<ICEDTO> getICE(@PathVariable Long id) {
        log.debug("REST request to get ICE : {}", id);
        Optional<ICEDTO> iCEDTO = iCEService.findOne(id);
        return ResponseUtil.wrapOrNotFound(iCEDTO);
    }

    /**
     * {@code GET  /ices/user/:id} : get the "id" iCE.
     *
     * @param id the id of the iCEDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the iCEDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ices/user/{id}")
    public List<ICEDTO> getUserIces(@PathVariable Long id) {
        log.debug("REST request to get ICE : {}", id);
        return iCEService.findUserIces(id);
    }

    /**
     * {@code DELETE  /ices/:id} : delete the "id" iCE.
     *
     * @param id the id of the iCEDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ices/{id}")
    public ResponseEntity<Void> deleteICE(@PathVariable Long id) {
        log.debug("REST request to delete ICE : {}", id);
        iCEService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
