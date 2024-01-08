package com.zerobase.mission.customer.controller;

import com.zerobase.mission.auth.security.TokenProvider;
import com.zerobase.mission.customer.dto.RegisterCustomerForm;
import com.zerobase.mission.customer.entity.Customer;
import com.zerobase.mission.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final TokenProvider tokenProvider;

    // 사용자 회원가입
    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterCustomerForm requestForm) {
        Customer customer = customerService.register(requestForm);
        return ResponseEntity.ok(customer);
    }

    // 사용자 정보조회
    @GetMapping("/customer/info/{id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> getCustomerInfo(@PathVariable Long id) {
        return ResponseEntity.ok().body(customerService.getInfo(id));
    }
}
