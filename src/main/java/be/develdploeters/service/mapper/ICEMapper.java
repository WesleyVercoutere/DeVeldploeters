package be.develdploeters.service.mapper;

import be.develdploeters.domain.ICE;
import be.develdploeters.service.dto.ICEDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link ICE} and its DTO {@link ICEDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ICEMapper extends EntityMapper<ICEDTO, ICE> {

    @Mapping(source = "user.id", target = "userId")
    ICEDTO toDto(ICE iCE);

    @Mapping(source = "userId", target = "user")
    ICE toEntity(ICEDTO iCEDTO);

    default ICE fromId(Long id) {
        if (id == null) {
            return null;
        }
        ICE iCE = new ICE();
        iCE.setId(id);
        return iCE;
    }
}
