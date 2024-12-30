package com.tennisclub.reservations.dto.create;

import com.tennisclub.reservations.dto.CourtDto;
import com.tennisclub.reservations.model.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDto {

    @NonNull
    private LocalDateTime from;

    @NonNull
    private LocalDateTime to;

    @NonNull
    private GameType gameType;

    @NonNull
    private UserCreateDto user;

    @NonNull
    private CourtDto court;
}
