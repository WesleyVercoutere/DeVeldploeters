package be.develdploeters.service.mapper;

import be.develdploeters.domain.Address;
import be.develdploeters.service.dto.AddressDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {



    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
