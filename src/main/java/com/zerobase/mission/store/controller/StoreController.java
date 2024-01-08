package com.zerobase.mission.store.controller;

import com.zerobase.mission.store.dto.CreateStoreForm;
import com.zerobase.mission.store.dto.UpdateStoreForm;
import com.zerobase.mission.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 매장 등록
    @PostMapping("/store/create")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> createStore(@RequestBody CreateStoreForm form) {
        return ResponseEntity.ok().body(storeService.createStore(form));
    }

    // 매장 수정
    @PutMapping("/store/update/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateStore(@PathVariable Long id, @RequestBody UpdateStoreForm form) {
        return ResponseEntity.ok().body(storeService.updateStore(id, form));
    }

    // 매장 삭제
    @DeleteMapping("/store/delete")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> deleteStore(@RequestParam("id") Long managerId, @RequestParam("store") Long storeId) {
        storeService.deleteStore(managerId, storeId);
        return ResponseEntity.ok("매장 삭제가 완료되었습니다.");
    }

    // 매장 목록 조회
    @GetMapping("/store/list")
    public ResponseEntity<?> getStoreList() {
        return ResponseEntity.ok().body(storeService.getStoreList());
    }

    // 매장 상세정보 조회
    @GetMapping("/store/detail/{name}")
    public ResponseEntity<?> getStoreInfo(@PathVariable String name) {
        return ResponseEntity.ok().body(storeService.detailStore(name));
    }

    // 매니저가 관리하는 매장 조회
    @GetMapping("/store/manager/list/{managerId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getStoreList(@PathVariable Long managerId) {
        return ResponseEntity.ok().body(storeService.getManagerStoreList(managerId));
    }
}
