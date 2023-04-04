package be.develdploeters.web.rest;

import be.develdploeters.security.AuthoritiesConstants;
import be.develdploeters.service.SponsorService;
import be.develdploeters.service.dto.SponsorDTO;
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
 * REST controller for managing {@link be.develdploeters.domain.Sponsor}.
 */
@RestController
@RequestMapping("/api")
public class SponsorResource {

    private final Logger log = LoggerFactory.getLogger(SponsorResource.class);

    private static final String ENTITY_NAME = "sponsor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SponsorService sponsorService;

    public SponsorResource(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    /**
     * {@code POST  /sponsors} : Create a new sponsor.
     *
     * @param sponsorDTO the sponsorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sponsorDTO, or with status {@code 400 (Bad Request)} if the sponsor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sponsors")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasRole(\"" + AuthoritiesConstants.BOARD  + "\")")
    public ResponseEntity<SponsorDTO> createSponsor(@RequestBody SponsorDTO sponsorDTO) throws URISyntaxException {
        log.debug("REST request to save Sponsor : {}", sponsorDTO);
        if (sponsorDTO.getId() != null) {
            throw new BadRequestAlertException("A new sponsor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SponsorDTO result = sponsorService.save(sponsorDTO);
        return ResponseEntity.created(new URI("/api/sponsors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sponsors} : Updates an existing sponsor.
     *
     * @param sponsorDTO the sponsorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sponsorDTO,
     * or with status {@code 400 (Bad Request)} if the sponsorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sponsorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sponsors")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasRole(\"" + AuthoritiesConstants.BOARD  + "\")")
    public ResponseEntity<SponsorDTO> updateSponsor(@RequestBody SponsorDTO sponsorDTO) throws URISyntaxException {
        log.debug("REST request to update Sponsor : {}", sponsorDTO);
        if (sponsorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SponsorDTO result = sponsorService.save(sponsorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sponsorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sponsors} : get all the sponsors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sponsors in body.
     */
    @GetMapping("/sponsors")
    public List<SponsorDTO> getAllSponsors() {
        log.debug("REST request to get all Sponsors");
        return sponsorService.findAll();
    }

    /**
     * GET  /sponsors : get all the sponsors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sponsors in body
     */
    @GetMapping("/public/sponsors")
    public List<SponsorDTO> getAllSponsorsPublic() {
        log.debug("REST request to get all Sponsors");
        return sponsorService.findAll();
    }

    /**
     * {@code GET  /sponsors/:id} : get the "id" sponsor.
     *
     * @param id the id of the sponsorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sponsorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sponsors/{id}")
    public ResponseEntity<SponsorDTO> getSponsor(@PathVariable Long id) {
        log.debug("REST request to get Sponsor : {}", id);
        Optional<SponsorDTO> sponsorDTO = sponsorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sponsorDTO);
    }

    /**
     * {@code DELETE  /sponsors/:id} : delete the "id" sponsor.
     *
     * @param id the id of the sponsorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sponsors/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasRole(\"" + AuthoritiesConstants.BOARD  + "\")")
    public ResponseEntity<Void> deleteSponsor(@PathVariable Long id) {
        log.debug("REST request to delete Sponsor : {}", id);
        sponsorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
