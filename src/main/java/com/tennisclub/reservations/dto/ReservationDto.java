package com.tennisclub.reservations.dto;

import com.tennisclub.reservations.model.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto extends BaseDto {

    @NonNull
    private LocalDate from;

    @NonNull
    private LocalDate to;

    @NonNull
    private GameType gameType;

    @NonNull
    private UserDto user;

    @NonNull
    private CourtDto court;
}
