package com.luisborrayo.adminproyectosytareas.Services;

import com.luisborrayo.adminproyectosytareas.Repositories.ProjectRepository;
import com.luisborrayo.adminproyectosytareas.models.Project;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProjectService {
    @Inject
    private ProjectRepository repoproject;
    public  List<Project> listarProject(){
        return repoproject.listar();
    }
    public Project guardarProyecto(Project project) {
        return repoproject.save(project);
    }
    public Project BuscarId(Long id) {
        return repoproject.buscarId(id);
    }
    public void modificarProyect(Project project) {
        repoproject.modificar(project);
    }

    public void EliminarProyect(Long id) {
        repoproject.eliminar(id);
    }
    public boolean nombreunico(String nombre, Long id) {
        return repoproject.listar().stream().filter(project -> !project.getId().equals(id))
                .noneMatch(project -> project.getName().equalsIgnoreCase(nombre));
    }
}
