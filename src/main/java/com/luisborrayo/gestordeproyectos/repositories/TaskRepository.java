package com.luisborrayo.adminproyectosytareas.Repositories;

import com.luisborrayo.adminproyectosytareas.models.Task;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@ApplicationScoped
public class TaskRepository {
    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(0);

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(counter.incrementAndGet());
        }
        tasks.put(task.getId(), task);
        return task;
    }
    public Task findById(Long id) {
        return tasks.get(id);
    }
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }
    public void delete(Long id) {
        tasks.remove(id);
    }
    public void toggleDone(Long id) {
        Task task = tasks.get(id);
        if (task != null) { task.setDone(!task.isDone()); }
    }

    public List<Task> findByPriority(Task.Priority priority) {
        return tasks.values().stream().filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void seed() {
        save(new Task(null, 1L, "Registrar productos iniciales", Task.Priority.HIGH,
                LocalDate.now().plusDays(3), false, "Ingresar datos de inventario de prueba en el sistema."));
        save(new Task(null, 1L, "Diseñar reporte de stock", Task.Priority.MEDIUM,
                LocalDate.now().plusDays(7), false, "Definir el formato del reporte para gerencia."));
        save(new Task(null, 1L, "Prueba de alertas de stock", Task.Priority.LOW,
                LocalDate.now().plusDays(5), true, "Verificar que se envíen notificaciones cuando el stock sea bajo."));
        save(new Task(null, 2L, "Diseñar interfaz de usuario", Task.Priority.HIGH,
                LocalDate.now().plusDays(10), false, "Mockups de pantallas principales de la app."));
        save(new Task(null, 2L, "Integrar sensor cardíaco", Task.Priority.MEDIUM,
                LocalDate.now().plusDays(15), false, "Conectar API del dispositivo con la app."));
    }
}
