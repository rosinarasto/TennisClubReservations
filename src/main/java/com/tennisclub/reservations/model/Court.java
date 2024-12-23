package com.tennisclub.reservations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Courts")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Court extends BaseEntity {

    private String name;

    private int number;

    @OneToMany
    private List<Reservation> reservations;

    @ManyToOne
    private Surface surface;
}
