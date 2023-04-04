package be.develdploeters.web.rest;

import be.develdploeters.service.CalendarService;
import be.develdploeters.service.dto.CalendarDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Activity.
 */
@RestController
@RequestMapping("/api")
public class CalendarResource {

    private final Logger log = LoggerFactory.getLogger(CalendarResource.class);

    private final CalendarService calendarService;

    public CalendarResource(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    /**
     * GET  /activities : get all the activities.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of activities in body
     */
    @GetMapping("/calendar")
    public List<CalendarDTO> getAllActivities(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Activities");
        return calendarService.findAll();
    }
}
