package com.luisborrayo.adminproyectosytareas.Repositories;

import com.luisborrayo.adminproyectosytareas.models.Project;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ProjectRepository {
    private final Map<Long, Project> projects = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(0);


    public Project save(Project project) {
        if (project.getId() == null) {
            project.setId(counter.incrementAndGet());
            project.setCreatedAt(LocalDateTime.now());
        }
        projects.put(project.getId(), project);
        return project;
    }

    public Project buscarId(Long id) {
        return projects.get(id);
    }

    public List<Project> listar() {
        return new ArrayList<>(projects.values());
    }

    public void modificar(Project project) {
        Project existe = buscarId(project.getId());
        if (existe != null) {
            existe.setName(project.getName());
            existe.setOwner(project.getOwner());
            existe.setStatus(project.getStatus());
            existe.setDescription(project.getDescription());
        }
    }
    public void eliminar(Long id) {
        projects.remove(id);
    }
    @PostConstruct
    void seed(){
        save(new Project(null, "Sistema de Inventario", "Ana López", Project.Status.ACTIVE, LocalDateTime.now(), "Aplicación para controlar productos en bodega y generar reportes de stock."));

        save(new Project(null, "Aplicación Móvil de Salud", "Carlos Méndez", Project.Status.ON_HOLD, LocalDateTime.now(), "App para monitoreo de ritmo cardíaco y recordatorios de medicinas."));

        save(new Project(null, "Rediseño de Marca", "María González", Project.Status.DONE, LocalDateTime.now(), "Proyecto de diseño gráfico y marketing para nueva imagen corporativa."));

    }
}
