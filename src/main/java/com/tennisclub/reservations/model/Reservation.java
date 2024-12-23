package com.tennisclub.reservations.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Reservations")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Reservation extends BaseEntity {

    private LocalDate from;

    private LocalDate to;

    @Enumerated(value = EnumType.STRING)
    private GameType gameType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Court court;
}
