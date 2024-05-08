package com.example.taskscheduler.controllers;

import com.example.taskscheduler.models.Task;
import com.example.taskscheduler.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/{projectId}/{userId}/create")
    public ResponseEntity<Task> createTaskInProject(@RequestParam String taskName, @RequestParam String description, @RequestParam String status, @PathVariable Integer projectId, @PathVariable Integer userId) {
        Task newTask = taskService.createTaskInProject(taskName, description, status, projectId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/{taskId}/name")
    public ResponseEntity<Task> updateTaskName(@PathVariable Integer taskId, @RequestParam String newName) {
        Task updatedTask = taskService.updateTaskName(taskId, newName);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{taskId}/description")
    public ResponseEntity<Task> updateTaskDescription(@PathVariable Integer taskId, @RequestParam String newDescription) {
        Task updatedTask = taskService.updateTaskDescription(taskId, newDescription);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Integer taskId, @RequestParam String newStatus) {
        Task updatedTask = taskService.updateTaskStatus(taskId, newStatus);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer taskId) {
        Task task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Integer taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.noContent().build();
    }
}