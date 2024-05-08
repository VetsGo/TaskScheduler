package com.example.taskscheduler.services;

import com.example.taskscheduler.models.Project;
import com.example.taskscheduler.models.User;
import com.example.taskscheduler.repositories.ProjectRepository;
import com.example.taskscheduler.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project createProject(String projectName, String description, Date startDate, Date endDate, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User was not found"));
        Project newProject = new Project();
        newProject.setProjectName(projectName);
        newProject.setDescription(description);
        newProject.setStartDate(startDate);
        newProject.setEndDate(endDate);
        newProject.setUserId(List.of(user));

        return projectRepository.save(newProject);
    }

    public Project getProjectById(Integer projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project was not found"));
    }

    public void deleteProjectById(Integer projectId) {
        projectRepository.deleteById(projectId);
    }

    public Project updateProjectName(Integer projectId, String newName) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project was not found"));

        project.setProjectName(newName);
        return projectRepository.save(project);
    }

    public Project updateProjectDescription(Integer projectId, String newDescription) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project was not found"));

        project.setDescription(newDescription);
        return projectRepository.save(project);
    }

    public Project updateProjectStartDate(Integer projectId, Date newStartDate) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project was not found"));

        project.setStartDate(newStartDate);
        return projectRepository.save(project);
    }

    public Project updateProjectEndDate(Integer projectId, Date newEndDate) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project was not found"));

        project.setEndDate(newEndDate);
        return projectRepository.save(project);
    }
}