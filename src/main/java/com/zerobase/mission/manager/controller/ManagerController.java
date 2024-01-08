package com.zerobase.mission.manager.controller;

import com.zerobase.mission.manager.dto.RegisterManagerForm;
import com.zerobase.mission.manager.entity.Manager;
import com.zerobase.mission.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    // 매니저 회원가입
    @PostMapping("/register/manager")
    public ResponseEntity<?> registerManager(@RequestBody RegisterManagerForm requestForm) {
        Manager manager = managerService.register(requestForm);
        return ResponseEntity.ok(manager);
    }

    // 매니저 정보조회
    @GetMapping("/manager/info/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getManagerInfo(@PathVariable Long id) {
        return ResponseEntity.ok().body(managerService.getInfo(id));
    }
}
