package com.zerobase.mission.reservation.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationForm {
    private Long userId;
    private Long storeId;

    private LocalDate reservationDate;
    private LocalTime reservationTime;
}
