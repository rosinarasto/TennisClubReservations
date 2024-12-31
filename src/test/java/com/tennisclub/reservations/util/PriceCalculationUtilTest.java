package com.tennisclub.reservations.util;

import com.tennisclub.reservations.model.GameType;
import com.tennisclub.reservations.model.factory.ReservationFactory;
import com.tennisclub.reservations.model.factory.SurfaceFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PriceCalculationUtilTest {

    private static final LocalDateTime DEFAULT_FROM = LocalDateTime.of(2024, 12, 31, 13, 30);
    private static final LocalDateTime DEFAULT_TO = LocalDateTime.of(2024, 12, 31, 15, 0);
    private static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(1.2);

    @Test
    public void testCalculatePriceForSingles() {
        var reservation = ReservationFactory
                .createDto(DEFAULT_FROM, DEFAULT_TO, GameType.SINGLES, SurfaceFactory.createDto(DEFAULT_PRICE));
        var price = PriceCalculationUtil.calculatePrice(reservation);
        assertThat(price).isEqualByComparingTo(BigDecimal.valueOf(108));
    }

    @Test
    public void testCalculatePriceForDoubles() {
        var reservation = ReservationFactory
                .createDto(DEFAULT_FROM, DEFAULT_TO, GameType.DOUBLES, SurfaceFactory.createDto(DEFAULT_PRICE));
        var price = PriceCalculationUtil.calculatePrice(reservation);
        assertThat(price).isEqualByComparingTo(BigDecimal.valueOf(162));
    }
}
