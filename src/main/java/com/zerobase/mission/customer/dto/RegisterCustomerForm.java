package com.zerobase.mission.customer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCustomerForm {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
