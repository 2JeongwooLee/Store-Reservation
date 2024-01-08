package com.zerobase.mission.store.service;

import com.zerobase.mission.common.exception.CustomException;
import com.zerobase.mission.common.exception.ErrorCode;
import com.zerobase.mission.manager.entity.Manager;
import com.zerobase.mission.manager.repository.ManagerRepository;
import com.zerobase.mission.store.dto.*;
import com.zerobase.mission.store.entity.Store;
import com.zerobase.mission.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;

    // 매장 정보 생성
    @Transactional
    public StoreDto createStore(CreateStoreForm form) {
        Manager manager = managerRepository.findById(form.getManagerId())
                .orElseThrow(() -> new CustomException(ErrorCode.MANAGER_NOT_FOUND));

        if (storeRepository.existsByStoreName(form.getStoreName())) {
            throw new CustomException(ErrorCode.ALREADY_EXISTED_STORE);
        }

        Store savedStore = storeRepository.save(Store.builder()
                .manager(manager)
                .storeName(form.getStoreName())
                .location(form.getLocation())
                .phoneNumber(form.getPhoneNumber())
                .description(form.getDescription())
                .build());

        return StoreDto.from(savedStore);
    }

    // 매장 삭제
    @Transactional
    public void deleteStore(Long managerId, Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        if (!store.getManager().getId().equals(managerId)) {
            throw new CustomException(ErrorCode.MANGER_STORE_NOT_MATCHED);
        }

        storeRepository.delete(store);
    }

    // 매장 정보 수정
    @Transactional
    public StoreDto updateStore(Long id, UpdateStoreForm form) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        if (!store.getManager().getId().equals(form.getManagerId())) {
            throw new CustomException(ErrorCode.MANGER_STORE_NOT_MATCHED);
        }

        store.setStoreName(form.getStoreName());
        store.setLocation(form.getLocation());
        store.setPhoneNumber(form.getPhoneNumber());
        store.setDescription(form.getDescription());

        return StoreDto.from(storeRepository.save(store));
    }

    // 매장 목록
    public List<GetStoreForm> getStoreList() {
        List<Store> storeList = storeRepository.findAll();

        if (storeList.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }

        return storeList.stream().map(GetStoreForm::from).collect(Collectors.toList());
    }


    // 매장 정보 확인
    public DetailStoreForm detailStore(String name) {
        Store store = storeRepository.findByStoreName(name)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        return DetailStoreForm.from(store);
    }


    // 매니저가 관리하는 매장 리스트
    public List<StoreDto> getManagerStoreList(Long managerId) {
        List<Store> storeList = storeRepository.findAllByManagerId(managerId);

        if (storeList.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }

        return storeList.stream().map(StoreDto::from).collect(Collectors.toList());
    }
}
