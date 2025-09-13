package com.luisborrayo.adminproyectosytareas.controllers;

import com.luisborrayo.adminproyectosytareas.Services.TaskService;
import com.luisborrayo.adminproyectosytareas.models.Task;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@SessionScoped
public class TaskController implements Serializable {

    @Inject
    private TaskService tasksService;

    private List<Task> tasks;
    private Task selectedTask;
    private Long projectId;

    @PostConstruct
    public void init() {
        selectedTask = new Task();
        tasks = List.of();
    }

    public void loadTasks() {
        if (projectId != null) {
            tasks = tasksService.findAll().stream()
                    .filter(t -> t.getProjectId() != null && t.getProjectId().equals(projectId))
                    .toList();
        } else {
            tasks = List.of();
        }
    }

    public void openNewTask() {
        if (projectId == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione un proyecto primero", null));
            return;
        }
        selectedTask = new Task();
        selectedTask.setProjectId(projectId);
    }

    public void selectTask(Task task) {
        this.selectedTask = task;
    }

    public void saveTask() {
        if (selectedTask == null) {
            return;
        }
        if (selectedTask.getTitle() == null || selectedTask.getTitle().isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El título es obligatorio", null));
            return;
        }
        if (selectedTask.getDueDate() != null && selectedTask.getDueDate().isBefore(LocalDate.now())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha debe ser hoy o futura", null));
            return;
        }
        // Asegurar que la tarea tenga projectId
        if (selectedTask.getProjectId() == null && projectId != null) {
            selectedTask.setProjectId(projectId);
        }
        tasksService.save(selectedTask);
        loadTasks();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarea guardada correctamente", null));
    }

    public void delete(Task task) {
        if (task == null) return;
        tasksService.delete(task.getId());
        loadTasks();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarea eliminada", null));
    }

    public void toggleDone(Task task) {
        if (task == null) return;
        tasksService.toggleDone(task.getId());
        loadTasks();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado actualizado", null));
    }

    public List<Task> getTasks() {
        if (tasks == null) tasks = List.of();
        return tasks;
    }

    public Task getSelectedTask() { return selectedTask; }
    public void setSelectedTask(Task selectedTask) { this.selectedTask = selectedTask; }
    public Long getProjectId() { return projectId; }

    // cuando cambie el projectId, recargamos tasks
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
        loadTasks();
    }

    public Task.Priority[] getPriorityEnum() {
        return Task.Priority.values();
    }
}
