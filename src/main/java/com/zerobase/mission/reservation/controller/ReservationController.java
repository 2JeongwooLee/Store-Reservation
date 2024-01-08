package com.zerobase.mission.reservation.controller;

import com.zerobase.mission.reservation.dto.ArrivalForm;
import com.zerobase.mission.reservation.dto.CreateReservationForm;
import com.zerobase.mission.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // 예약 등록
    @PostMapping("/reservation/create")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationForm form) {
        return ResponseEntity.ok().body(reservationService.createReservation(form));
    }

    // 매니저가 해당 매장에 대한 예약 조회
    @GetMapping("/reservation/list/{managerId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getReservationList(@PathVariable Long managerId) {
        return ResponseEntity.ok().body(reservationService.getReservationList(managerId));
    }

    // 매니저가 해당 예약 승인
    @PutMapping("/reservation/approval/{reservationId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> approvalReservationStatus(@PathVariable Long reservationId) {
        return ResponseEntity.ok().body(reservationService.approvalReservationStatus(reservationId));
    }

    // 매니저가 해당 예약 거절
    @DeleteMapping("/reservation/deny/{reservationId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> denyReservation(@PathVariable Long reservationId) {
        reservationService.denyReservation(reservationId);
        return ResponseEntity.ok().body("예약이 거절되었습니다.");
    }


    // 사용자가 도착상태 변경
    @PutMapping("/reservation/kiosk/{reservationId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> updateArrivalStatusAtKiosk(@PathVariable Long reservationId, @RequestBody ArrivalForm form) {
        return ResponseEntity.ok().body(reservationService.updateArrivalStatus(reservationId, form));
    }


    // 사용자가 예약 취소
    @DeleteMapping("/reservation/cancel/{reservationId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok().body("예약이 취소되었습니다.");
    }

}
