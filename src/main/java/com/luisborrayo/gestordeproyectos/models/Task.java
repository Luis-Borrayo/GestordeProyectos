package com.luisborrayo.gestordeproyectos.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Task implements Serializable {
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    private Long id;
    private Long projectId;
    private String title;
    private Priority priority;
    private LocalDate dueDate;
    private boolean done;
    private String notes;

    public Task() {
    }

    public Task(Long id, Long projectId, String title, Priority priority, LocalDate dueDate, boolean done, String notes) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.done = done;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Task)) return false;
        return Objects.equals(id, ((Task)obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}