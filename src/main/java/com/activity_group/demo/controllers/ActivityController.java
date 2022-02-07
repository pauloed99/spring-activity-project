package com.activity_group.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import com.activity_group.demo.models.Activity;
import com.activity_group.demo.services.ActivityService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ActivityController {
    private ActivityService activityService;

    @GetMapping("activities")
    public ResponseEntity<List<Activity>> getAll(@RequestParam(required = false) String description) {
        if(description == null)
            return ResponseEntity.ok(activityService.findAll());
        return ResponseEntity.ok(activityService.findByDescription(description));
    }

    @GetMapping("activities/{id}")
    public ResponseEntity<Activity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.findById(id));
    }

    @PostMapping("activityGroups/{id}/activities")
    public ResponseEntity<Activity> post(@PathVariable Long id, @RequestBody @Valid Activity activity) {
        return new ResponseEntity<>(activityService.save(activity, id), HttpStatus.CREATED);
    }

    @PutMapping("activities")
    public ResponseEntity<Void> put(@RequestBody @Valid Activity activity) {
        activityService.update(activity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("activities/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("activityGroups/{id}/activities")
    public ResponseEntity<Void> deleteAllByActivityGroup(@PathVariable Long id) {
        activityService.deleteAllActivitiesFromActivityGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
