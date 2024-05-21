package com.example.taskscheduler.services;

import com.example.taskscheduler.models.Project;
import com.example.taskscheduler.models.User;
import com.example.taskscheduler.repositories.ProjectRepository;
import com.example.taskscheduler.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void createProjectTest() {
        String projectName = "Project";
        String description = "Description";
        Date startDate = new Date();
        Date endDate = new Date();
        Integer userId = 1;

        User user = new User();
        user.setUserId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Project project = new Project();
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.getUsers().add(user);

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project newProject = projectService.createProject(projectName, description, startDate, endDate, userId);

        assertNotNull(newProject);
        assertEquals(projectName, newProject.getProjectName());
        assertEquals(description, newProject.getDescription());
        assertEquals(startDate, newProject.getStartDate());
        assertEquals(endDate, newProject.getEndDate());
        verify(userRepository, times(1)).findById(userId);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void getProjectByIdTest() {
        Integer projectId = 1;
        Project project = new Project();
        project.setProjectId(projectId);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        Project foundProject = projectService.getProjectById(projectId);

        assertNotNull(foundProject);
        assertEquals(projectId, foundProject.getProjectId());
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    void deleteProjectByIdTest() {
        Integer projectId = 1;
        projectService.deleteProjectById(projectId);
        verify(projectRepository, times(1)).deleteById(projectId);
    }

    @Test
    void updateProjectNameTest() {
        Integer projectId = 1;
        String newName = "Name";
        Project project = new Project();
        project.setProjectId(projectId);
        project.setProjectName(newName);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updatedProject = projectService.updateProjectName(projectId, newName);

        assertNotNull(updatedProject);
        assertEquals(newName, updatedProject.getProjectName());
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void updateProjectDescriptionTest() {
        Integer projectId = 1;
        String newDescription = "Description";
        Project project = new Project();
        project.setProjectId(projectId);
        project.setDescription(newDescription);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updatedProject = projectService.updateProjectDescription(projectId, newDescription);

        assertNotNull(updatedProject);
        assertEquals(newDescription, updatedProject.getDescription());
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void updateProjectStartDateTest() {
        Integer projectId = 1;
        Date newStartDate = new Date();
        Project project = new Project();
        project.setProjectId(projectId);
        project.setStartDate(newStartDate);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updatedProject = projectService.updateProjectStartDate(projectId, newStartDate);

        assertNotNull(updatedProject);
        assertEquals(newStartDate, updatedProject.getStartDate());
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void updateProjectEndDateTest() {
        Integer projectId = 1;
        Date newEndDate = new Date();
        Project project = new Project();
        project.setProjectId(projectId);
        project.setEndDate(newEndDate);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updatedProject = projectService.updateProjectEndDate(projectId, newEndDate);

        assertNotNull(updatedProject);
        assertEquals(newEndDate, updatedProject.getEndDate());
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectRepository, times(1)).save(any(Project.class));
    }
}