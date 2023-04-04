package be.develdploeters.service.mapper;

import be.develdploeters.domain.Activity;
import be.develdploeters.service.dto.ActivityDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Activity} and its DTO {@link ActivityDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReportMapper.class, AddressMapper.class, UserMapper.class})
public interface ActivityMapper extends EntityMapper<ActivityDTO, Activity> {

    default Activity toEntity(ActivityDTO activityDTO) {
        throw new UnsupportedOperationException("Can not create Activity object!");
    }

}
