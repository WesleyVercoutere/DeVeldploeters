package be.develdploeters.service.mapper;

import be.develdploeters.domain.Sponsor;
import be.develdploeters.service.dto.SponsorDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Sponsor} and its DTO {@link SponsorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SponsorMapper extends EntityMapper<SponsorDTO, Sponsor> {



    default Sponsor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sponsor sponsor = new Sponsor();
        sponsor.setId(id);
        return sponsor;
    }
}
