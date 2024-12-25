package com.tennisclub.reservations.model;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "court")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "surface_id")
    private Surface surface;
}
