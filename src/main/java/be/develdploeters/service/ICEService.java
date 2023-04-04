package be.develdploeters.service;

import be.develdploeters.domain.ICE;
import be.develdploeters.domain.User;
import be.develdploeters.repository.ICERepository;
import be.develdploeters.service.dto.ICEDTO;
import be.develdploeters.service.mapper.ICEMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ICE}.
 */
@Service
@Transactional
public class ICEService {

    private final Logger log = LoggerFactory.getLogger(ICEService.class);
    private final ICERepository iCERepository;
    private final ICEMapper iCEMapper;

    private final UserService userService;

    public ICEService(ICERepository iCERepository,
                      ICEMapper iCEMapper,
                      UserService userService) {
        this.iCERepository = iCERepository;
        this.iCEMapper = iCEMapper;
        this.userService = userService;
    }

    /**
     * Save a iCE.
     *
     * @param iCEDTO the entity to save.
     * @return the persisted entity.
     */
    public ICEDTO save(ICEDTO iCEDTO) {
        log.debug("Request to save ICE : {}", iCEDTO);
        ICE iCE = iCEMapper.toEntity(iCEDTO);
        iCE = iCERepository.save(iCE);
        return iCEMapper.toDto(iCE);
    }

    /**
     * Save a iCE.
     *
     * @param iceDTO the entity to save
     * @return the persisted entity
     */
    public ICEDTO create(ICEDTO iceDTO) {
        log.debug("Request to create ICE : {}", iceDTO);
        ICE ice = iCEMapper.toEntity(iceDTO);

        User user = userService.getCurrentUser();
        ice.setUser(user);

        ice = iCERepository.save(ice);
        return iCEMapper.toDto(ice);
    }

    /**
     * Get all the iCES.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ICEDTO> findAll() {
        log.debug("Request to get all ICES");
        return iCERepository.findAll().stream()
            .map(iCEMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the iCES from the current logged in user.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ICEDTO> findAllCurrentUser() {
        log.debug("Request to get all ICES from the current logged in user");

        User user = userService.getCurrentUser();

        return user.getIces()
            .stream()
            .map(iCEMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one iCE by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ICEDTO> findOne(Long id) {
        log.debug("Request to get ICE : {}", id);
        return iCERepository.findById(id)
            .map(iCEMapper::toDto);
    }

    /**
     * Delete the iCE by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ICE : {}", id);
        iCERepository.deleteById(id);
    }

    public List<ICEDTO> findUserIces(Long id) {
        return iCEMapper.toDto(iCERepository.findAllByUserId(id));
    }
}
