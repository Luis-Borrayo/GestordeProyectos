package com.luisborrayo.gestordeproyectos.services;

import com.luisborrayo.gestordeproyectos.repositories.TaskRepository;
import com.luisborrayo.gestordeproyectos.models.Task;
import com.luisborrayo.gestordeproyectos.repositories.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TaskService {
    @Inject
    private TaskRepository taskrepo;
    public Task save(Task task) {
        return taskrepo.save(task);
    }
    public Task findById(Long id) {
        return taskrepo.findById(id);
    }
    public List<Task> findAll() {
        return taskrepo.findAll();
    }
    public void delete(Long id) {
        taskrepo.delete(id);
    }
    public void toggleDone(Long id) {
        taskrepo.toggleDone(id);
    }
    public List<Task> findByPriority(Task.Priority priority) {
        return taskrepo.findByPriority(priority);
    }


}
