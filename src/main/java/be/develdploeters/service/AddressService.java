package be.develdploeters.service;

import be.develdploeters.domain.Address;
import be.develdploeters.domain.User;
import be.develdploeters.repository.AddressRepository;
import be.develdploeters.service.dto.AddressDTO;
import be.develdploeters.service.mapper.AddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Address}.
 */
@Service
@Transactional
public class AddressService {

    private final Logger log = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    private final UserService userService;

    public AddressService(AddressRepository addressRepository,
                          AddressMapper addressMapper,
                          UserService userService) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.userService = userService;
    }

    /**
     * Save a address.
     *
     * @param addressDTO the entity to save.
     * @return the persisted entity.
     */
    public AddressDTO save(AddressDTO addressDTO) {
        log.debug("Request to save Address : {}", addressDTO);
        Address address = addressMapper.toEntity(addressDTO);
        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    /**
     * Get all the addresses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AddressDTO> findAll() {
        log.debug("Request to get all Addresses");
        return addressRepository.findAll().stream()
            .map(addressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one address by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AddressDTO> findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        return addressRepository.findById(id)
            .map(addressMapper::toDto);
    }

    /**
     * Delete the address by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.deleteById(id);
    }

    public AddressDTO findCurrentUser() {
        User user = userService.getCurrentUser();
        Address userAddress = user.getAddress();

        if(user.getAddress() == null) {
            userAddress = new Address();
            addressRepository.save(userAddress);

            user.setAddress(userAddress);
            userService.saveUser(user);
        }
        return addressMapper.toDto(userAddress);
    }
}
