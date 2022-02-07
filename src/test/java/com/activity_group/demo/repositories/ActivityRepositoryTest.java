package com.activity_group.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.activity_group.demo.models.Activity;
import com.activity_group.demo.utils.Helpers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests of activity repository")
public class ActivityRepositoryTest {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityGroupRepository activityGroupRepository;
    
    @Test
    @DisplayName("Should be find all activities of database")
    void findAll() {
        activityGroupRepository.save(Helpers.createActivityGroup());
        activityRepository.save(Helpers.createActivity());
        List<Activity> activities = activityRepository.findAll();
        Assertions.assertThat(activities).isNotEmpty();
        Assertions.assertThat(activities.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should be find one activity by your id")
    void findOneActivityById() {
        activityGroupRepository.save(Helpers.createActivityGroup());
        Activity activity = activityRepository.save(Helpers.createActivity());
        Optional<Activity> activityOptional = activityRepository.findById(1L);
        Assertions.assertThat(activityOptional).contains(activity);
    }

    @Test
    @DisplayName("Should be find one activity by your description")
    void findOneActivityByDescription() {
        activityGroupRepository.save(Helpers.createActivityGroup());
        Activity activity = activityRepository.save(Helpers.createActivity());
        List<Activity> activities = activityRepository.findByDescriptionIgnoreCaseContaining("interativ");
        Assertions.assertThat(activities.get(0).getDescription()).isEqualTo(activity.getDescription());
    }

    @Test
    @DisplayName("Should be delete one activity by your id")
    void deleteOneActivityById() {
        activityGroupRepository.save(Helpers.createActivityGroup());
        Activity activity = activityRepository.save(Helpers.createActivity());
        activityRepository.deleteById(activity.getId());
        Optional<Activity> activityOptional = activityRepository.findById(activity.getId());
        Assertions.assertThat(activityOptional).isEmpty();
    }

    @Test
    @DisplayName("Should be find all activities by your activityGroupId")
    void findAllActivitiesByActivityGroupId() {
        activityGroupRepository.save(Helpers.createActivityGroup());
        Activity activity = activityRepository.save(Helpers.createActivity());
        List<Activity> activities = activityRepository.findByActivityGroupId(activity.getActivityGroup().getId());
        Assertions.assertThat(activities).isNotEmpty();
    }

    @Test
    @DisplayName("Should be update one activity by your id")
    void updateOneActivityByYourId() {
        activityGroupRepository.save(Helpers.createActivityGroup());
        Activity activity = activityRepository.save(Helpers.createActivity());
        activity.setDescription("Programar site em React");
        activityRepository.save(activity);
        Assertions.assertThat(activity.getDescription()).isEqualTo("Programar site em React");
    }

    @Test
    @DisplayName("Should be delete activites of an activityGroupId")
    void deleteAllActivitiesFromActivityGroup() {
        activityGroupRepository.save(Helpers.createActivityGroup());
        Activity activity = activityRepository.save(Helpers.createActivity());
        activityRepository.deleteByActivityGroupId(activity.getActivityGroup().getId());
        Optional<Activity> activityOptional = activityRepository.findById(activity.getId());
        Assertions.assertThat(activityOptional).isEmpty();
    }
}
