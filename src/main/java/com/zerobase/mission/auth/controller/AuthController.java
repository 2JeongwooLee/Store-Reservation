package com.zerobase.mission.auth.controller;


import com.zerobase.mission.auth.dto.LoginForm;
import com.zerobase.mission.auth.security.TokenProvider;
import com.zerobase.mission.auth.service.AuthService;
import com.zerobase.mission.customer.entity.Customer;
import com.zerobase.mission.manager.entity.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController{

    private final AuthService authService;
    private final TokenProvider tokenProvider;

    // 매니저 로그인
    @PostMapping("/login/manager")
    public ResponseEntity<?> loginManager(@RequestBody LoginForm requestForm) {
        Manager manager = authService.authenticateManager(requestForm);
        String token = tokenProvider.generateToken(manager.getEmail(), manager.getMemberType());

        return ResponseEntity.ok(token);
    }

    // 사용자 로그인
    @PostMapping("/login/customer")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginForm requestForm) {
        Customer customer = authService.authenticateCustomer(requestForm);
        String token = tokenProvider.generateToken(customer.getEmail(), customer.getMemberType());

        return ResponseEntity.ok(token);
    }
}
