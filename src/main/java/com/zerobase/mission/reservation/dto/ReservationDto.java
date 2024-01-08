package com.zerobase.mission.reservation.dto;

import com.zerobase.mission.reservation.entity.Reservation;
import com.zerobase.mission.reservation.type.ArrivalStatusType;
import com.zerobase.mission.reservation.type.ReservationStatusType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long reservationId;
    private String customerName;
    private String customerPhoneNumber;
    private String storeName;

    private ReservationStatusType reservationStatusType;
    private ArrivalStatusType arrivalStatusType;

    private LocalDate reservationDate;
    private LocalTime reservationTime;

    public static ReservationDto from(Reservation reservation) {
        return ReservationDto.builder()
                .reservationId(reservation.getId())
                .customerName(reservation.getCustomer().getName())
                .customerPhoneNumber(reservation.getCustomer().getPhoneNumber())
                .storeName(reservation.getStore().getStoreName())
                .reservationStatusType(reservation.getReservationStatusType())
                .arrivalStatusType(reservation.getArrivalStatusType())
                .reservationDate(reservation.getReservationDate())
                .reservationTime(reservation.getReservationTime())
                .build();
    }
}
