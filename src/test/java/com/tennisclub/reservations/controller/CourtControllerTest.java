package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.model.factory.ReservationFactory;
import com.tennisclub.reservations.service.CourtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourtService courtService;

    @Test
    public void courtReservationsByCourtNumber_returnsReservations() throws Exception {
        var reservationDTOs = List.of(
                ReservationFactory.createDto(getTime(12, 0), getTime(13, 30)),
                ReservationFactory.createDto(getTime(14, 0), getTime(15, 50))
        );

        when(courtService.findReservations(4))
                .thenReturn(reservationDTOs);

        mockMvc.perform(get("/api/court/4/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    private LocalDateTime getTime(int hour, int minute) {
        return LocalDateTime.of(2024, 12, 31, hour, minute);
    }
}
