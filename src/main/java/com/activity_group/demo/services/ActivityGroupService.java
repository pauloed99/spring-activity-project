package com.activity_group.demo.services;

import java.util.List;

import com.activity_group.demo.models.ActivityGroup;
import com.activity_group.demo.repositories.ActivityGroupRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActivityGroupService {
    private ActivityGroupRepository activityGroupRepository;

    public List<ActivityGroup> findAll() {
        return activityGroupRepository.findAll();
    }

    public ActivityGroup save(ActivityGroup activityGroup) {
        return activityGroupRepository.save(activityGroup);
    }

    public ActivityGroup findById(Long id) {
        return activityGroupRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "O grupo de atividades com o id " + id + " não foi encontrada"));
    }

    public void update(ActivityGroup activityGroup) {
        findById(activityGroup.getId());
        activityGroupRepository.save(activityGroup);
    }

    public void deleteById(Long id) {
        findById(id);
        activityGroupRepository.deleteById(id);
    }
}
