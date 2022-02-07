package com.activity_group.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.activity_group.demo.models.ActivityGroup;
import com.activity_group.demo.utils.Helpers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests of ActivityGroup Repository")
public class ActivityGroupRepositoryTest {
    @Autowired
    private ActivityGroupRepository activityGroupRepository;

    @Test
    @DisplayName("Should be return all activity groups of database")
    void findAllActivityGroups() {
        activityGroupRepository.save(Helpers.createActivityGroup());
        List<ActivityGroup> activityGroups = activityGroupRepository.findAll();
        Assertions.assertThat(activityGroups).isNotEmpty();
        Assertions.assertThat(activityGroups.size()).isEqualTo(1);
        Assertions.assertThat(activityGroups.get(0).getTitle()).isEqualTo("Programar em Java");
    }

    @Test
    @DisplayName("Should be return one activity group by your id")
    void findOneActivityGroup() {
        ActivityGroup activityGroup = activityGroupRepository.save(Helpers.createActivityGroup());
        Optional<ActivityGroup> activityGroupOptional = activityGroupRepository.findById(activityGroup.getId());
        Assertions.assertThat(activityGroupOptional).contains(activityGroup);
    }

    @Test
    @DisplayName("Should be delete one activity group by your id")
    void deleteOneActivityGroup() {
        ActivityGroup activityGroup = activityGroupRepository.save(Helpers.createActivityGroup());
        activityGroupRepository.deleteById(activityGroup.getId());
        Optional<ActivityGroup> activityGroupOptional = activityGroupRepository.findById(activityGroup.getId());
        Assertions.assertThat(activityGroupOptional).isEmpty();
    }

    @Test
    @DisplayName("Should be update one activity group by your id")
    void updateOneActivityGroup() {
        ActivityGroup activityGroup = activityGroupRepository.save(Helpers.createActivityGroup());
        activityGroup.setTitle("Programar em NodeJS");
        activityGroupRepository.save(activityGroup);
        Assertions.assertThat(activityGroup.getTitle()).isEqualTo("Programar em NodeJS");
    }
}
