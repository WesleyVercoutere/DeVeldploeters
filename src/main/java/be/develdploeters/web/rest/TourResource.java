package be.develdploeters.web.rest;

import be.develdploeters.service.activity.impl.TourService;
import be.develdploeters.service.dto.ActivityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing {@link be.develdploeters.domain.Tour}.
 */
@RestController
@RequestMapping("/api")
public class TourResource {

    private final Logger log = LoggerFactory.getLogger(TourResource.class);

    private static final String ENTITY_NAME = "tour";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TourService tourService;

    public TourResource(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/externalTours/{date}")
    public List<ActivityDTO> getExternalTour(@PathVariable String date) {
        log.debug("REST request to get Tours from mountainbike.be : {}", date);
        return tourService.findExternalTours(date);
    }
}
