package com.activity_group.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import com.activity_group.demo.models.ActivityGroup;
import com.activity_group.demo.services.ActivityGroupService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("activityGroups")
public class ActivityGroupController {
    private ActivityGroupService activityGroupService;

    @GetMapping
    public ResponseEntity<List<ActivityGroup>> getAll() {
        return ResponseEntity.ok(activityGroupService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ActivityGroup> getById(@PathVariable Long id) {
        return ResponseEntity.ok(activityGroupService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ActivityGroup> post(@RequestBody @Valid ActivityGroup activityGroup) {
        return new ResponseEntity<>(activityGroupService.save(activityGroup), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> put(@RequestBody @Valid ActivityGroup activityGroup) {
        activityGroupService.update(activityGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityGroupService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
