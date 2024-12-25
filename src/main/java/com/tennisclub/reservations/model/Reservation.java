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
    @Column(name = "game_type")
    private GameType gameType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;
}
