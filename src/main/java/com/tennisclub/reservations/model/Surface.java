package com.tennisclub.reservations.model;

import jakarta.persistence.Column;
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

    @Column(name = "minute_price")
    private double minutePrice;

    private String name;

    @OneToMany(mappedBy = "surface")
    private List<Court> courts;
}
