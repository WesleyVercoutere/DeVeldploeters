package be.develdploeters.service.mapper;

import be.develdploeters.domain.Organization;
import be.develdploeters.service.dto.OrganizationDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Organization} and its DTO {@link OrganizationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {



    default Organization fromId(Long id) {
        if (id == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }
}
