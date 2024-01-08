package com.zerobase.mission.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {
    CUSTOMER("ROLE_CUSTOMER"),
    MANAGER("ROLE_MANAGER");

    private final String description;
}
