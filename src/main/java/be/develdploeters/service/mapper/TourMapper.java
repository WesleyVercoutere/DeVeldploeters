package be.develdploeters.service.mapper;

import be.develdploeters.domain.Address;
import be.develdploeters.domain.Organization;
import be.develdploeters.domain.Tour;
import be.develdploeters.service.dto.ActivityDTO;
import be.develdploeters.service.dto.TourDTO;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Mapper for the entity {@link Tour} and its DTO {@link TourDTO}.
 */
@Service
public class TourMapper implements EntityMapper<ActivityDTO, Tour> {

    @Override
    public ActivityDTO toDto(Tour tour) {
        if (tour == null)
            return null;

        ActivityDTO dto = new ActivityDTO();

        dto.setId(tour.getId());
        dto.setType(tour.getType());
        dto.setTitle(tour.getTitle());
        dto.setDate(tour.getDate());
        dto.setTime(tour.getTime());
        dto.setStartTime(tour.getStartTime());
        dto.setDistance(tour.getDistance());

        if(tour.getOrganization() == null) {
            Organization org = new Organization();
            tour.setOrganization(org);
        }

        dto.setOrganisationId(tour.getOrganization().getId());
        dto.setName(tour.getOrganization().getName());
        dto.setWebsite(tour.getOrganization().getWebsite());
        dto.setEmail(tour.getOrganization().getEmail());

        dto.setLocation(tour.getLocation());
        if(tour.getAddress() == null) {
            Address address = new Address();
            tour.setAddress(address);
        }

        dto.setAddressId(tour.getAddress().getId());
        dto.setStreet(tour.getAddress().getStreet());
        dto.setNumber(tour.getAddress().getNumber());
        dto.setZip(tour.getAddress().getZipCode());
        dto.setCity(tour.getAddress().getCity());

        return dto;
    }

    @Override
    public Tour toEntity(ActivityDTO dto) {
        if(dto == null)
            return null;

        Tour tour = new Tour();

        tour.setId(dto.getId());
        tour.setType(dto.getType());
        tour.setTitle(dto.getTitle());
        tour.setDate(dto.getDate());
        tour.setTime(dto.getTime());
        tour.setStartTime(dto.getStartTime());
        tour.setDistance(dto.getDistance());

        Organization org = new Organization();

        org.setId(dto.getOrganisationId());
        org.setName(dto.getName());
        org.setWebsite(dto.getWebsite());
        org.setEmail(dto.getEmail());

        tour.setOrganization(org);

        tour.setLocation(dto.getLocation());
        Address address = new Address();

        address.setId(dto.getAddressId());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setZipCode(dto.getZip());
        address.setCity(dto.getCity());

        tour.setAddress(address);

        return tour;
    }

}

