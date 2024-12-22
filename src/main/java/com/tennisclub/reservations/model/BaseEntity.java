package com.tennisclub.reservations.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public abstract class BaseEntity {

    private Long id;

    private LocalDateTime creationDate;

    private LocalDateTime modificationDate;

    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
    }

    protected void onUpdate() {
        this.modificationDate = LocalDateTime.now();
    }
}
