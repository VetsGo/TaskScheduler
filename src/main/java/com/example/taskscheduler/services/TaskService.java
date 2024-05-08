package com.example.taskscheduler.services;

import com.example.taskscheduler.models.Project;
import com.example.taskscheduler.models.Task;
import com.example.taskscheduler.models.User;
import com.example.taskscheduler.repositories.ProjectRepository;
import com.example.taskscheduler.repositories.TaskRepository;
import com.example.taskscheduler.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Task createTaskInProject(String taskName, String description, String status, Integer projectId, Integer userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project was not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User was not found"));

        Task newTask = new Task();
        newTask.setTaskName(taskName);
        newTask.setDescription(description);
        newTask.setStatus(status);
        newTask.setProjectId(project);
        newTask.setUserId(List.of(user));

        return taskRepository.save(newTask);
    }

    public Task updateTaskName(Integer taskId, String newName) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task was not found"));

        task.setTaskName(newName);
        return taskRepository.save(task);
    }

    public Task updateTaskDescription(Integer taskId, String newDescription) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task was not found"));

        task.setDescription(newDescription);
        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Integer taskId, String newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task was not found"));

        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    public Task getTaskById(Integer taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task was not found"));
    }

    public void deleteTaskById(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}