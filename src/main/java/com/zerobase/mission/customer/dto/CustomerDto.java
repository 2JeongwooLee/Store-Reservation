package com.zerobase.mission.customer.dto;

import com.zerobase.mission.common.type.MemberType;
import com.zerobase.mission.customer.entity.Customer;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private MemberType memberType;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    public static CustomerDto from(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .memberType(customer.getMemberType())
                .name(customer.getName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
