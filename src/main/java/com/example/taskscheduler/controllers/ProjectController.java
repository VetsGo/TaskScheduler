package com.example.taskscheduler.controllers;

import com.example.taskscheduler.models.Project;
import com.example.taskscheduler.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/{userId}/create")
    public ResponseEntity<Project> createProject(@RequestParam String name, @RequestParam String description, @RequestParam Date start, @RequestParam Date end, @PathVariable Integer userId) {
        Project newProject = projectService.createProject(name, description, start, end, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProject);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer projectId) {
        Project project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{projectId}/name")
    public ResponseEntity<Project> updateProjectName(@PathVariable Integer projectId, @RequestParam String newname) {
        Project updatedProject = projectService.updateProjectName(projectId, newname);
        return ResponseEntity.ok(updatedProject);
    }

    @PutMapping("/{projectId}/description")
    public ResponseEntity<Project> updateProjectDescription(@PathVariable Integer projectId, @RequestParam String newdescription) {
        Project updatedProject = projectService.updateProjectDescription(projectId, newdescription);
        return ResponseEntity.ok(updatedProject);
    }

    @PutMapping("/{projectId}/startDate")
    public ResponseEntity<Project> updateProjectStartDate(@PathVariable Integer projectId, @RequestParam Date newstart) {
        Project updatedProject = projectService.updateProjectStartDate(projectId, newstart);
        return ResponseEntity.ok(updatedProject);
    }

    @PutMapping("/{projectId}/endDate")
    public ResponseEntity<Project> updateProjectEndDate(@PathVariable Integer projectId, @RequestParam Date newend) {
        Project updatedProject = projectService.updateProjectEndDate(projectId, newend);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Integer projectId) {
        projectService.deleteProjectById(projectId);
        return ResponseEntity.noContent().build();
    }
}