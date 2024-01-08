package com.zerobase.mission.customer.service;

import com.zerobase.mission.common.exception.CustomException;
import com.zerobase.mission.common.exception.ErrorCode;
import com.zerobase.mission.common.type.MemberType;
import com.zerobase.mission.customer.dto.CustomerDto;
import com.zerobase.mission.customer.dto.RegisterCustomerForm;
import com.zerobase.mission.customer.entity.Customer;
import com.zerobase.mission.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    // 사용자 회원가입
    @Transactional
    public Customer register(RegisterCustomerForm form) {
        if (customerRepository.existsByEmail(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        }

        form.setPassword(passwordEncoder.encode(form.getPassword()));

        Customer customer = customerRepository.save(Customer.builder()
                .memberType(MemberType.CUSTOMER)
                .name(form.getName())
                .email(form.getEmail())
                .password(form.getPassword())
                .phoneNumber(form.getPhoneNumber())
                .build());

        return customer;
    }


    // 사용자 정보 조회
    public CustomerDto getInfo(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));

        return CustomerDto.from(customer);
    }


}
