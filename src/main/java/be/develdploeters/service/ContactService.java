package be.develdploeters.service;

import be.develdploeters.service.dto.ContactDTO;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final MailService mailService;

    private final String EMAIL = "wesley.vercoutere@gmail.com";

    public ContactService(MailService mailService) {
        this.mailService = mailService;
    }

    public void send(ContactDTO contactDTO) {

        if (contactDTO.getStreet() != null || contactDTO.getCity() != null)
            return;

        if (contactDTO.getNumber() != 0 || contactDTO.getZip() != 0)
            return;

        System.out.println(contactDTO.getStreet());
        System.out.println(contactDTO.getNumber());
        System.out.println(contactDTO.getZip());
        System.out.println(contactDTO.getCity());

        StringBuilder content = new StringBuilder();
        content.append("<p>").append("Naam: ").append(contactDTO.getFirstName()).append(" ").append(contactDTO.getLastName()).append("</p>");
        content.append("<p>").append("E-mail: ").append(contactDTO.getEmail()).append("</p>");
        content.append("<p>").append("</p>");
        content.append("<p>").append("Bericht: ").append("</p>");
        content.append("<p>").append(contactDTO.getText()).append("</p>");

        mailService.sendEmail(EMAIL, "contact", content.toString(), false, true);
    }
}
