package com.zerobase.mission.auth.service;

import com.zerobase.mission.auth.dto.LoginForm;
import com.zerobase.mission.common.exception.CustomException;
import com.zerobase.mission.common.exception.ErrorCode;
import com.zerobase.mission.common.type.MemberType;
import com.zerobase.mission.customer.entity.Customer;
import com.zerobase.mission.customer.repository.CustomerRepository;
import com.zerobase.mission.manager.entity.Manager;
import com.zerobase.mission.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (managerRepository.existsByEmail(email)) {
            Manager manager = managerRepository.findByEmail(email)
                    .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

            return User.withUsername(manager.getEmail())
                    .password(passwordEncoder.encode(manager.getPassword()))
                    .roles(String.valueOf(MemberType.MANAGER))
                    .build();
        } else if (customerRepository.existsByEmail(email)){
            Customer customer = customerRepository.findByEmail(email)
                    .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

            return User.withUsername(customer.getEmail())
                    .password(passwordEncoder.encode(customer.getPassword()))
                    .roles(String.valueOf(MemberType.CUSTOMER))
                    .build();
        }

        throw new UsernameNotFoundException("email을 찾을 수 없습니다.");
    }

    // 사용자 인증
    public Customer authenticateCustomer(LoginForm form) {
        Customer customer = customerRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(form.getPassword(), customer.getPassword())) {
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        }

        return customer;
    }

    // 매니저 인증
    public Manager authenticateManager(LoginForm form) {
        Manager manager = managerRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(form.getPassword(), manager.getPassword())) {
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        }

        return manager;
    }
}
