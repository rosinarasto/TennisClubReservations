package com.tennisclub.reservations.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Court extends BaseEntity {

    private String name;

    private int number;

    private List<Reservation> reservations;

    private Surface surface;
}
