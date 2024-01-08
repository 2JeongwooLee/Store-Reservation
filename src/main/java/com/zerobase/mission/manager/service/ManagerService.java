package com.zerobase.mission.manager.service;

import com.zerobase.mission.common.type.MemberType;
import com.zerobase.mission.common.exception.CustomException;
import com.zerobase.mission.common.exception.ErrorCode;
import com.zerobase.mission.manager.dto.ManagerDto;
import com.zerobase.mission.manager.dto.RegisterManagerForm;
import com.zerobase.mission.manager.entity.Manager;
import com.zerobase.mission.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final PasswordEncoder passwordEncoder;
    private final ManagerRepository managerRepository;


    // 매니저 회원가입
    @Transactional
    public Manager register(RegisterManagerForm form) {
        if (managerRepository.existsByEmail(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        }

        form.setPassword(passwordEncoder.encode(form.getPassword()));

        Manager manager = managerRepository.save(Manager.builder()
                .memberType(MemberType.MANAGER)
                .name(form.getName())
                .email(form.getEmail())
                .password(form.getPassword())
                .phoneNumber(form.getPhoneNumber())
                .build());

        return manager;
    }


    // 매니저 정보 조회
    public ManagerDto getInfo(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MANAGER_NOT_FOUND));

        return ManagerDto.from(manager);
    }


}
