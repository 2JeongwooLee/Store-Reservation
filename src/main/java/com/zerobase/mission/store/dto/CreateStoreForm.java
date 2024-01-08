package com.zerobase.mission.store.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStoreForm {

    private Long managerId;

    private String storeName;

    private String location;

    private String phoneNumber;

    private String description;
}
