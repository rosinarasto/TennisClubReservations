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
public class Surface extends BaseEntity {

    private int minutePrice;

    private String name;

    private List<Court> courts;
}
