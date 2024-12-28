package com.tennisclub.reservations.dto.create;

import com.tennisclub.reservations.dto.SurfaceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtCreateDto {

    @NonNull
    private String name;

    @NonNull
    private Integer number;

    @NonNull
    private SurfaceDto surface;
}
