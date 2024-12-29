package com.tennisclub.reservations.service.impl;

import com.tennisclub.reservations.dto.ReservationDto;
import com.tennisclub.reservations.model.GameType;
import com.tennisclub.reservations.service.PriceCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;

@Service
public class PriceCalculationServiceImpl implements PriceCalculationService {

    public BigDecimal calculatePrice(ReservationDto reservation) {
        var surfacePrice = reservation.getCourt().getSurface().getMinutePrice();

        var duration = Duration.between(reservation.getFrom(), reservation.getTo());
        var durationMinutes = BigDecimal.valueOf(duration.getSeconds() / 60);

        var multiplier = BigDecimal.valueOf(getMultiplier(reservation.getGameType()));

        return surfacePrice
                .multiply(durationMinutes)
                .multiply(multiplier);
    }

    private double getMultiplier(GameType gameType) {
        return switch (gameType) {
            case SINGLES -> 1.0;
            case DOUBLES -> 1.5;
            default -> throw new IllegalArgumentException("unknown game type " + gameType);
        };
    }
}
