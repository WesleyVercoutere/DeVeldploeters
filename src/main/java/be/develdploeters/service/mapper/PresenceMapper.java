package be.develdploeters.service.mapper;

import be.develdploeters.domain.Presence;
import be.develdploeters.service.dto.PresenceDTO;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity {@link Presence} and its DTO {@link PresenceDTO}.
 */
@Service
public class PresenceMapper implements EntityMapper<PresenceDTO, Presence> {

    @Override
    public Presence toEntity(PresenceDTO dto) {
        if (dto == null)
            return null;

        Presence presence = new Presence();

        presence.setId(dto.getId());
        presence.setPresent(dto.isPresent());

        return presence;
    }

    @Override
    public PresenceDTO toDto(Presence presence) {
        if (presence == null)
            return null;

        PresenceDTO dto = new PresenceDTO();

        dto.setId(presence.getId());
        dto.setPresent(presence.isPresent());

        dto.setActivityId(presence.getActivity().getId());
        dto.setUserId(presence.getUser().getId());

        dto.setUsername((presence.getUser().getFirstName() == null)
            ? ""
            : presence.getUser().getFirstName() + " " + presence.getUser().getLastName());

        return dto;
    }
}
