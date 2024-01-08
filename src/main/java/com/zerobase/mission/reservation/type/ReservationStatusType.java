package com.zerobase.mission.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatusType {

    STANDBY("대기"),
    APPROVAL("승인"),
    USE_COMPLETE("사용 완료");

    private final String description;
}
