package com.example.taskscheduler.controllers.html;

import com.example.taskscheduler.models.Project;
import com.example.taskscheduler.models.User;
import com.example.taskscheduler.services.ProjectService;
import com.example.taskscheduler.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectHtml {
    private final ProjectService projectService;
    private final UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/create")
    public String showCreateProjectForm(Model model, @RequestParam Integer userId) {
        model.addAttribute("project", new Project());
        model.addAttribute("userId", userId);
        model.addAttribute("projects", projectService.getAllProjects());
        return "project";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute Project project, @RequestParam Integer userId, Model model) {
        Project newProject = projectService.createProject(project.getProjectName(), project.getDescription(), project.getStartDate(), project.getEndDate(), userId);
        model.addAttribute("project", newProject);
        model.addAttribute("projects", projectService.getAllProjects());
        return "redirect:/projects/" + newProject.getProjectId();
    }

    @GetMapping("/{projectId}")
    public String getProjectById(@PathVariable Integer projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("project", project);
        return "projectinfo";
    }

    @GetMapping("/{projectId}/update")
    public String showUpdateProjectForm(@PathVariable Integer projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("project", project);
        return "project-update";
    }

    @PostMapping("/{projectId}/update")
    public String updateProject(@PathVariable Integer projectId, @ModelAttribute Project project) {
        projectService.updateProjectName(projectId, project.getProjectName());
        projectService.updateProjectDescription(projectId, project.getDescription());
        projectService.updateProjectStartDate(projectId, project.getStartDate());
        projectService.updateProjectEndDate(projectId, project.getEndDate());
        return "redirect:/projects/" + projectId;
    }

    @DeleteMapping("/{projectId}")
    public String deleteProjectById(@PathVariable Integer projectId) {
        projectService.deleteProjectById(projectId);
        return "redirect:/projects";
    }
}
