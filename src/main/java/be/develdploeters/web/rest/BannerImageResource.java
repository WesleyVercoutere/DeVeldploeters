package be.develdploeters.web.rest;

import be.develdploeters.security.AuthoritiesConstants;
import be.develdploeters.service.BannerImageService;
import be.develdploeters.service.dto.BannerImageDTO;
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
 * REST controller for managing {@link be.develdploeters.domain.BannerImage}.
 */
@RestController
@RequestMapping("/api")
public class BannerImageResource {

    private final Logger log = LoggerFactory.getLogger(BannerImageResource.class);

    private static final String ENTITY_NAME = "bannerImage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BannerImageService bannerImageService;

    public BannerImageResource(BannerImageService bannerImageService) {
        this.bannerImageService = bannerImageService;
    }

    /**
     * {@code POST  /banner-images} : Create a new bannerImage.
     *
     * @param bannerImageDTO the bannerImageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bannerImageDTO, or with status {@code 400 (Bad Request)} if the bannerImage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/banner-images")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasRole(\"" + AuthoritiesConstants.BOARD  + "\")")
    public ResponseEntity<BannerImageDTO> createBannerImage(@RequestBody BannerImageDTO bannerImageDTO) throws URISyntaxException {
        log.debug("REST request to save BannerImage : {}", bannerImageDTO);
        if (bannerImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new bannerImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BannerImageDTO result = bannerImageService.save(bannerImageDTO);
        return ResponseEntity.created(new URI("/api/banner-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /banner-images} : Updates an existing bannerImage.
     *
     * @param bannerImageDTO the bannerImageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bannerImageDTO,
     * or with status {@code 400 (Bad Request)} if the bannerImageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bannerImageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/banner-images")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasRole(\"" + AuthoritiesConstants.BOARD  + "\")")
    public ResponseEntity<BannerImageDTO> updateBannerImage(@RequestBody BannerImageDTO bannerImageDTO) throws URISyntaxException {
        log.debug("REST request to update BannerImage : {}", bannerImageDTO);
        if (bannerImageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BannerImageDTO result = bannerImageService.save(bannerImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bannerImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /banner-images} : get all the bannerImages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bannerImages in body.
     */
    @GetMapping("/banner-images")
    public List<BannerImageDTO> getAllBannerImages() {
        log.debug("REST request to get all BannerImages");
        return bannerImageService.findAll();
    }

    /**
     * GET  /banner-images : get all active banner images for the front-end.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bannerImages in body
     */
    @GetMapping("/public/banner-images")
    public List<BannerImageDTO> getAllActiveBannerImages() {
        log.debug("REST request to get all BannerImages");
        return bannerImageService.findActive();
    }

    /**
     * {@code GET  /banner-images/:id} : get the "id" bannerImage.
     *
     * @param id the id of the bannerImageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bannerImageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/banner-images/{id}")
    public ResponseEntity<BannerImageDTO> getBannerImage(@PathVariable Long id) {
        log.debug("REST request to get BannerImage : {}", id);
        Optional<BannerImageDTO> bannerImageDTO = bannerImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bannerImageDTO);
    }

    /**
     * {@code DELETE  /banner-images/:id} : delete the "id" bannerImage.
     *
     * @param id the id of the bannerImageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/banner-images/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasRole(\"" + AuthoritiesConstants.BOARD  + "\")")
    public ResponseEntity<Void> deleteBannerImage(@PathVariable Long id) {
        log.debug("REST request to delete BannerImage : {}", id);
        bannerImageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
