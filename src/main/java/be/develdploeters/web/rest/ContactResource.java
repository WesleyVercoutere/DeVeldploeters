package be.develdploeters.web.rest;

import be.develdploeters.service.ContactService;
import be.develdploeters.service.dto.ActivityDTO;
import be.develdploeters.service.dto.ContactDTO;
import be.develdploeters.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class ContactResource {

    private final Logger log = LoggerFactory.getLogger(ActivityResource.class);
    private static final String ENTITY_NAME = "contact";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactService contactService;

    public ContactResource(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/public/contact")
    public ResponseEntity<ContactDTO> createActivity(@RequestBody ContactDTO contactDTO) throws URISyntaxException {
        log.debug("REST request to send a contactForm : {}", contactDTO);
        contactService.send(contactDTO);
        return new ResponseEntity("send", HttpStatus.OK);
    }

}
