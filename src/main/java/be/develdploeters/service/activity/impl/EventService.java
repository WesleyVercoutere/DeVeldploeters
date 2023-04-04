package be.develdploeters.service.activity.impl;

import be.develdploeters.domain.Event;
import be.develdploeters.domain.Tour;
import be.develdploeters.repository.EventRepository;
import be.develdploeters.repository.TourRepository;
import be.develdploeters.service.activity.IActivityService;
import be.develdploeters.service.dto.ActivityDTO;
import be.develdploeters.service.mapper.EventMapper;
import be.develdploeters.service.mapper.TourMapper;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IActivityService {

    private EventRepository eventRepository;
    private EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public ActivityDTO findById(Long id) {
        return eventMapper.toDto(eventRepository.getOne(id));
    }

    @Override
    public ActivityDTO save(ActivityDTO activityDTO) {
        Event event = eventMapper.toEntity(activityDTO);
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

}
