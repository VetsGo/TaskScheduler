package com.example.taskscheduler.controllers.html;

import com.example.taskscheduler.models.Task;
import com.example.taskscheduler.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskHtml {
    private final TaskService taskService;

    @GetMapping("/create")
    public String showCreateTaskForm(Model model, @RequestParam Integer userId, @RequestParam Integer projectId) {
        model.addAttribute("task", new Task());
        model.addAttribute("userId", userId);
        model.addAttribute("projectId", projectId);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task";
    }

    @PostMapping("/create")
    public String createTaskInProject(@ModelAttribute Task task, @RequestParam Integer projectId, @RequestParam Integer userId, Model model) {
        Task newTask = taskService.createTaskInProject(task.getTaskName(), task.getDescription(), task.getStatus(), projectId, userId);
        model.addAttribute("task", newTask);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/tasks/" + newTask.getTaskId();
    }

    @GetMapping("/{taskId}")
    public String getTaskById(@PathVariable Integer taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        return "taskinfo";
    }

    @GetMapping("/{taskId}/update")
    public String showUpdateTaskForm(@PathVariable Integer taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        return "task-update";
    }

    @GetMapping("/{taskId}/delete")
    public String showDeleteTaskForm(@PathVariable Integer taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        return "task-delete";
    }

    @PostMapping("/{taskId}/update")
    public String updateTask(@PathVariable Integer taskId, @ModelAttribute Task task) {
        taskService.updateTaskName(taskId, task.getTaskName());
        taskService.updateTaskDescription(taskId, task.getDescription());
        taskService.updateTaskStatus(taskId, task.getStatus());
        return "redirect:/tasks/" + taskId;
    }

    @GetMapping("/deleted")
    public String showDeletedPage() {
        return "deleted";
    }

    @PostMapping("/{taskId}/delete")
    public String deleteTaskById(@PathVariable Integer taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/tasks/deleted";
    }
}
