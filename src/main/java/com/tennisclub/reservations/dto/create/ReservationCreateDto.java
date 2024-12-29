package com.tennisclub.reservations.dto.create;

import com.tennisclub.reservations.dto.CourtDto;
import com.tennisclub.reservations.model.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDto {

    @NonNull
    private LocalDate from;

    @NonNull
    private LocalDate to;

    @NonNull
    private GameType gameType;

    @NonNull
    private UserCreateDto user;

    @NonNull
    private CourtDto court;
}
