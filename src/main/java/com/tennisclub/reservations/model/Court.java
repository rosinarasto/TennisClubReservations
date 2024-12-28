package com.tennisclub.reservations.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "\"courts\"")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Court extends BaseEntity {

    private String name;

    private int number;

    @OneToMany(mappedBy = "court")
    @OrderBy("creationDate")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "surface_id")
    private Surface surface;
}
