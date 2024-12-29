package com.tennisclub.reservations.service;

import com.tennisclub.reservations.dto.ReservationDto;

import java.math.BigDecimal;

public interface PriceCalculationService {

    BigDecimal calculatePrice(ReservationDto reservation);
}
