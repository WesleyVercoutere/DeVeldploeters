package be.develdploeters.service.mapper;

import be.develdploeters.domain.Address;
import be.develdploeters.domain.Event;
import be.develdploeters.service.dto.ActivityDTO;
import be.develdploeters.service.dto.EventDTO;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity {@link Event} and its DTO {@link EventDTO}.
 */
@Service
public class EventMapper implements EntityMapper<ActivityDTO, Event> {

    @Override
    public ActivityDTO toDto(Event event) {
        if (event == null)
            return null;

        ActivityDTO dto = new ActivityDTO();

        dto.setId(event.getId());
        dto.setType(event.getType());
        dto.setTitle(event.getTitle());
        dto.setDate(event.getDate());
        dto.setEndDate(event.getEndDate());
        dto.setTime(event.getTime());

        if(event.getAddress() == null) {
            Address address = new Address();
            event.setAddress(address);
        }

        dto.setLocation(event.getLocation());
        dto.setAddressId((event.getAddress().getId()));
        dto.setStreet((event.getAddress().getStreet()));
        dto.setNumber((event.getAddress().getNumber()));
        dto.setZip(event.getAddress().getZipCode());
        dto.setCity((event.getAddress().getCity()));

        return dto;
    }

    @Override
    public Event toEntity(ActivityDTO dto) {
        if(dto == null)
            return null;

        Event event = new Event();

        event.setId(dto.getId());
        event.setType(dto.getType());
        event.setTitle(dto.getTitle());
        event.setDate(dto.getDate());
        event.setEndDate(dto.getEndDate());
        event.setTime(dto.getTime());

        event.setLocation(dto.getLocation());
        Address address = new Address();

        address.setId(dto.getAddressId());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setZipCode(dto.getZip());
        address.setCity(dto.getCity());

        event.setAddress(address);

        return event;
    }

}
