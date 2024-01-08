package com.zerobase.mission.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArrivalStatusType {

    READY("대기"),
    ARRIVED("도착 완료"),
    NO_SHOW("노쇼");

    private final String description;
}
