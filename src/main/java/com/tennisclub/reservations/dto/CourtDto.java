package com.tennisclub.reservations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtDto extends BaseDto {

    @NonNull
    private String name;

    @NonNull
    private Integer number;

    @NonNull
    private SurfaceDto surface;
}
