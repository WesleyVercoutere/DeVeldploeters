package be.develdploeters.service.activity;

import be.develdploeters.service.dto.ActivityDTO;

public interface IActivityService {

    ActivityDTO findById(Long id);

    ActivityDTO save(ActivityDTO activityDTO);
}
