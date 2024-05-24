package com.example.taskscheduler.services;

import com.example.taskscheduler.models.Project;
import com.example.taskscheduler.models.Task;
import com.example.taskscheduler.models.User;
import com.example.taskscheduler.repositories.ProjectRepository;
import com.example.taskscheduler.repositories.TaskRepository;
import com.example.taskscheduler.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskServiceIntegrationTest {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testCreateTaskInProject() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user = userRepository.save(user);

        Project project = new Project();
        project.setProjectName("New Project");
        project = projectRepository.save(project);

        Task task = taskService.createTaskInProject("New Task", "Description", "Open", project.getProjectId(), user.getUserId());

        assertNotNull(task);
        assertEquals("New Task", task.getTaskName());
        assertEquals(project, task.getProjectId());
        assertTrue(task.getUsers().contains(user));
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setTaskName("Test Task");
        task = taskRepository.save(task);

        Task foundTask = taskService.getTaskById(task.getTaskId());

        assertNotNull(foundTask);
        assertEquals(task.getTaskId(), foundTask.getTaskId());
    }

    @Test
    void testDeleteTaskById() {
        Task task = new Task();
        task.setTaskName("Test Task");
        task = taskRepository.save(task);

        taskService.deleteTaskById(task.getTaskId());

        Task finalTask = task;
        assertThrows(RuntimeException.class, () -> taskService.getTaskById(finalTask.getTaskId()));
    }

    @Test
    void testUpdateTaskName() {
        Task task = new Task();
        task.setTaskName("Old Task Name");
        task = taskRepository.save(task);

        Task updatedTask = taskService.updateTaskName(task.getTaskId(), "New Task Name");

        assertNotNull(updatedTask);
        assertEquals("New Task Name", updatedTask.getTaskName());
    }

    @Test
    void testUpdateTaskDescription() {
        Task task = new Task();
        task.setDescription("Old Description");
        task = taskRepository.save(task);

        Task updatedTask = taskService.updateTaskDescription(task.getTaskId(), "New Description");

        assertNotNull(updatedTask);
        assertEquals("New Description", updatedTask.getDescription());
    }

    @Test
    void testUpdateTaskStatus() {
        Task task = new Task();
        task.setStatus("Old Status");
        task = taskRepository.save(task);

        Task updatedTask = taskService.updateTaskStatus(task.getTaskId(), "New Status");

        assertNotNull(updatedTask);
        assertEquals("New Status", updatedTask.getStatus());
    }
}