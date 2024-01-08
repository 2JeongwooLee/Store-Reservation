package com.zerobase.mission.store.dto;

import com.zerobase.mission.manager.entity.Manager;
import com.zerobase.mission.store.entity.Store;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private Manager manager;
    private String storeName;
    private String location;
    private String phoneNumber;
    private String description;

    public static StoreDto from(Store store) {
        return StoreDto.builder()
                .manager(store.getManager())
                .storeName(store.getStoreName())
                .location(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .description(store.getDescription())
                .build();
    }
}
