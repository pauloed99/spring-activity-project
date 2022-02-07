package com.activity_group.demo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.activity_group.demo.models.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByDescriptionIgnoreCaseContaining(String description);
    List<Activity> findByActivityGroupId(Long activityGroupId);
    @Transactional
    void deleteByActivityGroupId(Long activityGroupId);
}
