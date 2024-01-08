package com.zerobase.mission.store.dto;

import com.zerobase.mission.store.entity.Store;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailStoreForm {
    private String storeName;
    private String location;
    private String phoneNumber;
    private String description;

    public static DetailStoreForm from(Store store) {
        return DetailStoreForm.builder()
                .storeName(store.getStoreName())
                .location(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .description(store.getDescription())
                .build();
    }
}
