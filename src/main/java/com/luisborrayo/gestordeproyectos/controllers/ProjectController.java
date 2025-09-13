package com.luisborrayo.adminproyectosytareas.controllers;

import com.luisborrayo.adminproyectosytareas.Services.ProjectService;
import com.luisborrayo.adminproyectosytareas.models.Project;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

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

    private Project slectedProject;
    private List<Project> projects;

    @PostConstruct
    public void init() {
        projects = serviceproject.listarProject();
    }

    public void crearNuevoProyecto() {
        slectedProject = new Project();
        slectedProject.setCreatedAt(LocalDateTime.now());
    }

    public void guardarPro() {
        if (slectedProject.getName() == null || slectedProject.getName().trim().isEmpty()
                || slectedProject.getOwner() == null || slectedProject.getOwner().trim().isEmpty()) {
            return;
        }
        if (!serviceproject.nombreunico(slectedProject.getName(), slectedProject.getId())) {
            return;
        }
        serviceproject.guardarProyecto(slectedProject);
        projects = serviceproject.listarProject();
    }

    public void editarProyecto(Project project) {
        slectedProject = project;
        if (project != null && project.getId() != null) {
            taskController.setProjectId(project.getId());
        }
    }

    public void eliminarProyecto(Project project) {
        serviceproject.EliminarProyect(project.getId());
        projects = serviceproject.listarProject();
    }

    public Project getSlectedProject() {
        return slectedProject;
    }

    public void setSlectedProject(Project slectedProject) {
        this.slectedProject = slectedProject;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
