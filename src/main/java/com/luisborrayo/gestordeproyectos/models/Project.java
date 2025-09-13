package com.luisborrayo.adminproyectosytareas.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Project implements  java.io.Serializable {

    public enum Status {
        ACTIVE,
        ON_HOLD,
        DONE
    }

    private Long id;
    private String name;
    private String owner;
    private Status status;
    private LocalDateTime createdAt;
    private String description;

    public Project() {
    }

    public Project(Long id, String name, String owner, Status status, LocalDateTime createdAt, String description) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.status = status;
        this.createdAt = createdAt;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof Project)) return false;
        return Objects.equals(id, ((Project)obj).id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
