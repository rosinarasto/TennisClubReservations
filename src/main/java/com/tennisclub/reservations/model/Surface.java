package com.tennisclub.reservations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Surfaces")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Surface extends BaseEntity {

    private int minutePrice;

    private String name;

    @OneToMany
    private List<Court> courts;
}
