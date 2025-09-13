package com.luisborrayo.gestordeproyectos.controllers;

import com.luisborrayo.gestordeproyectos.services.ProjectService;
import com.luisborrayo.gestordeproyectos.models.Project;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@SessionScoped
public class ProjectController implements Serializable {

    @Inject
    private ProjectService serviceproject;

    @Inject
    private TaskController taskController;

    private Project selectedProject; // corregido
    private List<Project> projects;

    @PostConstruct
    public void init() {
        projects = serviceproject.listarProject();
    }

    public void crearNuevoProyecto() {
        selectedProject = new Project();
        selectedProject.setCreatedAt(LocalDateTime.now());
    }

    public void guardarPro() {
        if (selectedProject.getName() == null || selectedProject.getName().trim().isEmpty()
                || selectedProject.getOwner() == null || selectedProject.getOwner().trim().isEmpty()) {
            return;
        }
        if (!serviceproject.nombreunico(selectedProject.getName(), selectedProject.getId())) {
            return;
        }
        serviceproject.guardarProyecto(selectedProject);
        projects = serviceproject.listarProject();
    }

    public void editarProyecto(Project project) {
        selectedProject = project;
        if (project != null && project.getId() != null) {
            taskController.setProjectId(project.getId());
        }
    }

    public void eliminarProyecto(Project project) {
        serviceproject.EliminarProyect(project.getId());
        projects = serviceproject.listarProject();
    }

    public void proyectoSeleccionado(SelectEvent<Project> event) {
        this.selectedProject = event.getObject();
        if (selectedProject != null) {
            taskController.setProjectId(selectedProject.getId());
        }
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
