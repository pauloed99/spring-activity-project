package com.activity_group.demo.services;

import java.util.List;

import com.activity_group.demo.models.Activity;
import com.activity_group.demo.models.ActivityGroup;
import com.activity_group.demo.repositories.ActivityGroupRepository;
import com.activity_group.demo.repositories.ActivityRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ActivityService {
    private ActivityRepository activityRepository;
    private ActivityGroupRepository activityGroupRepository;

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public List<Activity> findAllActivitiesByActivityGroupId(Long activityGroupId) {
        activityGroupRepository.findById(activityGroupId)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "O grupo de atividades com o id " + activityGroupId + " não foi encontrado"));
        return activityRepository.findByActivityGroupId(activityGroupId);
    }

    public List<Activity> findByDescription(String description) {
        return activityRepository.findByDescriptionIgnoreCaseContaining(description);
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "A atividade com o id " + id + " não foi encontrada"));
    }

    public Activity save(Activity activity, Long activityGroupId) {
        ActivityGroup activityGroup = activityGroupRepository.findById(activityGroupId)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "O grupo de atividades com o id " + activityGroupId + " não foi encontrado"));
        activity.setActivityGroup(activityGroup);
        return activityRepository.save(activity);
    }

    public void update(Activity activity) {
        findById(activity.getId());
        activityRepository.save(activity);
    }

    public void deleteById(Long id) {
        findById(id);
        activityRepository.deleteById(id);
    }

    public void deleteAllActivitiesFromActivityGroup(Long activityGroupId) {
        activityGroupRepository.findById(activityGroupId)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "O grupo de atividades com o id " + activityGroupId + " não foi encontrado"));
        activityRepository.deleteByActivityGroupId(activityGroupId);
    }
}
