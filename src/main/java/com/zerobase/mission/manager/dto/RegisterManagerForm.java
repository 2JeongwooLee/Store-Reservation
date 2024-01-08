package com.zerobase.mission.manager.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterManagerForm {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
