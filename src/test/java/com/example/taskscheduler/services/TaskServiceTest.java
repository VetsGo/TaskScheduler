package com.example.taskscheduler.services;

import com.example.taskscheduler.models.Project;
import com.example.taskscheduler.models.Task;
import com.example.taskscheduler.models.User;
import com.example.taskscheduler.repositories.ProjectRepository;
import com.example.taskscheduler.repositories.TaskRepository;
import com.example.taskscheduler.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTaskInProjectTest() {
        Integer projectId = 1;
        Integer userId = 1;
        String taskName = "Task";
        String description = "Description";
        String status = "Status";
        Project project = new Project();
        project.setProjectId(projectId);
        User user = new User();
        user.setUserId(userId);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setStatus(status);
        task.setProjectId(project);
        task.setUserId(List.of(user));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task newTask = taskService.createTaskInProject(taskName, description, status, projectId, userId);

        assertNotNull(newTask);
        assertEquals(taskName, newTask.getTaskName());
        assertEquals(description, newTask.getDescription());
        assertEquals(status, newTask.getStatus());
        assertEquals(projectId, newTask.getProjectId().getProjectId());
        assertEquals(userId, newTask.getUserId().get(0).getUserId());
        verify(projectRepository, times(1)).findById(projectId);
        verify(userRepository, times(1)).findById(userId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void getTaskByIdTest() {
        Integer taskId = 1;
        Task task = new Task();
        task.setTaskId(taskId);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task retrievedTask = taskService.getTaskById(taskId);

        assertNotNull(retrievedTask);
        assertEquals(taskId, retrievedTask.getTaskId());
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void deleteTaskByIdTest() {
        Integer taskId = 1;
        Task task = new Task();
        task.setTaskId(taskId);
        doNothing().when(taskRepository).deleteById(taskId);

        taskService.deleteTaskById(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void updateTaskNameTest() {
        Integer taskId = 1;
        String newName = "Name";
        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName(newName);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.updateTaskName(taskId, newName);

        assertNotNull(updatedTask);
        assertEquals(newName, updatedTask.getTaskName());
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void updateTaskDescriptionTest() {
        Integer taskId = 1;
        String newDescription = "Description";
        Task task = new Task();
        task.setTaskId(taskId);
        task.setDescription(newDescription);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.updateTaskDescription(taskId, newDescription);

        assertNotNull(updatedTask);
        assertEquals(newDescription, updatedTask.getDescription());
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void updateTaskStatusTest() {
        Integer taskId = 1;
        String newStatus = "Status";
        Task task = new Task();
        task.setTaskId(taskId);
        task.setStatus(newStatus);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.updateTaskStatus(taskId, newStatus);

        assertNotNull(updatedTask);
        assertEquals(newStatus, updatedTask.getStatus());
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }
}