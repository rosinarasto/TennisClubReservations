package com.tennisclub.reservations.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    private boolean deleted = false;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modificationDate = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleted = true;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
