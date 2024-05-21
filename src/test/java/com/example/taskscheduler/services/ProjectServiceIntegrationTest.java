package com.example.taskscheduler.services;

import com.example.taskscheduler.models.Project;
import com.example.taskscheduler.models.User;
import com.example.taskscheduler.repositories.ProjectRepository;
import com.example.taskscheduler.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectServiceIntegrationTest {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void testCreateProject() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password".getBytes());
        user = userRepository.save(user);

        Project project = projectService.createProject("New Project", "Description", new Date(), new Date(), user.getUserId());

        assertNotNull(project);
        assertEquals("New Project", project.getProjectName());
        assertTrue(project.getUsers().contains(user));
    }

    @Test
    void testGetProjectById() {
        Project project = new Project();
        project.setProjectName("Test Project");
        project = projectRepository.save(project);

        Project foundProject = projectService.getProjectById(project.getProjectId());

        assertNotNull(foundProject);
        assertEquals(project.getProjectId(), foundProject.getProjectId());
    }

    @Test
    void testDeleteProjectById() {
        Project project = new Project();
        project.setProjectName("Test Project");
        project = projectRepository.save(project);

        projectService.deleteProjectById(project.getProjectId());

        Project finalProject = project;
        assertThrows(RuntimeException.class, () -> projectService.getProjectById(finalProject.getProjectId()));
    }

    @Test
    void testUpdateProjectName() {
        Project project = new Project();
        project.setProjectName("Old Project Name");
        project = projectRepository.save(project);

        Project updatedProject = projectService.updateProjectName(project.getProjectId(), "New Project Name");

        assertNotNull(updatedProject);
        assertEquals("New Project Name", updatedProject.getProjectName());
    }

    @Test
    void testUpdateProjectDescription() {
        Project project = new Project();
        project.setDescription("Old Description");
        project = projectRepository.save(project);

        Project updatedProject = projectService.updateProjectDescription(project.getProjectId(), "New Description");

        assertNotNull(updatedProject);
        assertEquals("New Description", updatedProject.getDescription());
    }

    @Test
    void testUpdateProjectStartDate() {
        Project project = new Project();
        project.setStartDate(new Date());
        project = projectRepository.save(project);

        Date newStartDate = new Date(System.currentTimeMillis() + 86400000); // +1 day
        Project updatedProject = projectService.updateProjectStartDate(project.getProjectId(), newStartDate);

        assertNotNull(updatedProject);
        assertEquals(newStartDate, updatedProject.getStartDate());
    }

    @Test
    void testUpdateProjectEndDate() {
        Project project = new Project();
        project.setEndDate(new Date());
        project = projectRepository.save(project);

        Date newEndDate = new Date(System.currentTimeMillis() + 86400000 * 2); // +2 days
        Project updatedProject = projectService.updateProjectEndDate(project.getProjectId(), newEndDate);

        assertNotNull(updatedProject);
        assertEquals(newEndDate, updatedProject.getEndDate());
    }
}