package be.develdploeters.service;

import be.develdploeters.domain.BannerImage;
import be.develdploeters.repository.BannerImageRepository;
import be.develdploeters.service.dto.BannerImageDTO;
import be.develdploeters.service.mapper.BannerImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BannerImage}.
 */
@Service
@Transactional
public class BannerImageService {

    private final Logger log = LoggerFactory.getLogger(BannerImageService.class);

    private final BannerImageRepository bannerImageRepository;

    private final BannerImageMapper bannerImageMapper;

    public BannerImageService(BannerImageRepository bannerImageRepository, BannerImageMapper bannerImageMapper) {
        this.bannerImageRepository = bannerImageRepository;
        this.bannerImageMapper = bannerImageMapper;
    }

    /**
     * Save a bannerImage.
     *
     * @param bannerImageDTO the entity to save.
     * @return the persisted entity.
     */
    public BannerImageDTO save(BannerImageDTO bannerImageDTO) {
        log.debug("Request to save BannerImage : {}", bannerImageDTO);
        BannerImage bannerImage = bannerImageMapper.toEntity(bannerImageDTO);
        bannerImage = bannerImageRepository.save(bannerImage);
        return bannerImageMapper.toDto(bannerImage);
    }

    /**
     * Get all the bannerImages.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BannerImageDTO> findAll() {
        log.debug("Request to get all BannerImages");
        return bannerImageRepository.findAll().stream()
            .map(bannerImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all active banner images.
     *
     * @return the list of active entities
     */
    @Transactional(readOnly = true)
    public List<BannerImageDTO> findActive() {
        log.debug("Request to get all BannerImages");
        return bannerImageRepository.findByActiveTrue().stream()
            .map(bannerImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bannerImage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BannerImageDTO> findOne(Long id) {
        log.debug("Request to get BannerImage : {}", id);
        return bannerImageRepository.findById(id)
            .map(bannerImageMapper::toDto);
    }

    /**
     * Delete the bannerImage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BannerImage : {}", id);
        bannerImageRepository.deleteById(id);
    }
}
