package com.tennisclub.reservations.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    private LocalDate from;

    private LocalDate to;

    private GameType gameType;

    private User user;

    private Court court;
}
