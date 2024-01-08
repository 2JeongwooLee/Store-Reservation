package com.zerobase.mission.store.dto;

import com.zerobase.mission.store.entity.Store;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStoreForm {
    String storeName;
    String location;

    public static GetStoreForm from(Store store) {
        return GetStoreForm.builder()
                .storeName(store.getStoreName())
                .location(store.getLocation())
                .build();
    }
}
