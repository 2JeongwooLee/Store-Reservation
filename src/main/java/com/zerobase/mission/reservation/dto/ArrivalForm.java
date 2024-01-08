package com.zerobase.mission.reservation.dto;

import com.zerobase.mission.reservation.type.ArrivalStatusType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArrivalForm {
    private LocalDateTime arrivalTime;
}
