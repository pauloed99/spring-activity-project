package com.activity_group.demo.utils;

import com.activity_group.demo.models.Activity;
import com.activity_group.demo.models.ActivityGroup;

public class Helpers {
    public static ActivityGroup createActivityGroup() {
        ActivityGroup activityGroup = new ActivityGroup();
        activityGroup.setId(1L);
        activityGroup.setTitle("Programar em Java");
        return activityGroup;
    }

    public static Activity createActivity() {
        Activity activity = new Activity();
        activity.setId(1L);
        activity.setDescription("Adicionar menu interativo");
        activity.setActivityGroup(createActivityGroup());
        return activity;
    }
}
