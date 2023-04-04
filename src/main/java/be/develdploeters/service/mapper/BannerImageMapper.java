package be.develdploeters.service.mapper;

import be.develdploeters.domain.BannerImage;
import be.develdploeters.service.dto.BannerImageDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link BannerImage} and its DTO {@link BannerImageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BannerImageMapper extends EntityMapper<BannerImageDTO, BannerImage> {

    default BannerImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        BannerImage bannerImage = new BannerImage();
        bannerImage.setId(id);
        return bannerImage;
    }
}
