package be.develdploeters.service.mapper;

import be.develdploeters.domain.User;
import be.develdploeters.service.dto.MemberDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO {@link MemberDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MemberMapper extends EntityMapper<MemberDTO, User> {

    default User fromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

}
