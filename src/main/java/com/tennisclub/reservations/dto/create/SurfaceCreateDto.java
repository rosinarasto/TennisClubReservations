package com.tennisclub.reservations.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurfaceCreateDto {

    @NonNull
    private BigDecimal minutePrice;

    @NonNull
    private String name;
}
