package be.develdploeters.service.activity.impl;

import be.develdploeters.domain.Tour;
import be.develdploeters.repository.TourRepository;
import be.develdploeters.service.ExternalTourDetailService;
import be.develdploeters.service.activity.IActivityService;
import be.develdploeters.service.dto.ActivityDTO;
import be.develdploeters.service.mapper.TourMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService implements IActivityService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final ExternalTourDetailService externalTourDetailService;

    public TourService(TourRepository tourRepository,
                       TourMapper tourMapper,
                       ExternalTourDetailService externalTourDetailService) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
        this.externalTourDetailService = externalTourDetailService;
    }

    @Override
    public ActivityDTO findById(Long id) {
        return tourMapper.toDto(tourRepository.getOne(id));
    }

    @Override
    public ActivityDTO save(ActivityDTO activityDTO) {
        Tour tour = tourMapper.toEntity(activityDTO);
        tour = tourRepository.save(tour);
        return tourMapper.toDto(tour);
    }

    public List<ActivityDTO> findExternalTours(String date) {
        return this.externalTourDetailService.getTourDataFromDate(date);
    }

}
