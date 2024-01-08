package com.zerobase.mission.manager.dto;

import com.zerobase.mission.common.type.MemberType;
import com.zerobase.mission.manager.entity.Manager;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
    private Long id;
    private MemberType memberType;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    public static ManagerDto from(Manager manager) {
        return ManagerDto.builder()
                .id(manager.getId())
                .memberType(manager.getMemberType())
                .name(manager.getName())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .phoneNumber(manager.getPhoneNumber())
                .build();
    }
}
