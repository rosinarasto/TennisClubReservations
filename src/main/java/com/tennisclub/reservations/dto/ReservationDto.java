package com.tennisclub.reservations.dto;

import com.tennisclub.reservations.model.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto extends BaseDto {

    @NonNull
    private LocalDateTime from;

    @NonNull
    private LocalDateTime to;

    @NonNull
    private GameType gameType;

    @NonNull
    private UserDto user;

    @NonNull
    private CourtDto court;
}
