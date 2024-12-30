package com.tennisclub.reservations.validator;

import com.tennisclub.reservations.dto.CourtDto;
import com.tennisclub.reservations.repository.CourtRepository;
import com.tennisclub.reservations.service.ReservationService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

@Slf4j
public class AvailableDateValidator implements ConstraintValidator<AvailableDate, Object> {

    private String courtField;
    private String fromField;
    private String toField;

    private final ReservationService reservationService;

    private final CourtRepository courtRepository;

    @Autowired
    public AvailableDateValidator(ReservationService reservationService, CourtRepository courtRepository) {
        this.reservationService = reservationService;
        this.courtRepository = courtRepository;
    }

    @Override
    public void initialize(AvailableDate constraintAnnotation) {
        this.courtField = constraintAnnotation.courtField();
        this.fromField = constraintAnnotation.fromField();
        this.toField = constraintAnnotation.toField();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        log.info("Checking availability of chosen date range.");

        CourtDto courtDto;
        LocalDateTime from;
        LocalDateTime to;

        try {
            courtDto = (CourtDto) PropertyUtils.getProperty(value, courtField);
            from = (LocalDateTime) PropertyUtils.getProperty(value, fromField);
            to = (LocalDateTime) PropertyUtils.getProperty(value, toField);
        } catch (InvocationTargetException | ClassCastException | IllegalAccessException | NoSuchMethodException ignored) {
            return false;
        }

        var court = courtRepository.findByCourtNumber(courtDto.getNumber());

        return court.filter(court1 -> reservationService.isDateAvailable(court1, from, to)).isPresent();

    }
}