package com.tennisclub.reservations.controller;

import com.tennisclub.reservations.model.factory.ReservationFactory;
import com.tennisclub.reservations.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.tennisclub.reservations.TestUtils.convertToJson;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReservationService reservationService;

    @Test
    public void createReservation_returnsPrice() throws Exception {
        var createDto = ReservationFactory.createCreateDto();
        var reservationDto = ReservationFactory.createDto();

        when(reservationService.create(createDto))
                .thenReturn(reservationDto);

        mockMvc.perform(post("/api/reservation")
                        .content(convertToJson(createDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(13.22));
    }

    private LocalDateTime getTime(int hour, int minute) {
        return LocalDateTime.of(2024, 12, 31, hour, minute);
    }
}
