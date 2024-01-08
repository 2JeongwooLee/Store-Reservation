package com.zerobase.mission.reservation.service;

import com.zerobase.mission.common.exception.CustomException;
import com.zerobase.mission.common.exception.ErrorCode;
import com.zerobase.mission.customer.entity.Customer;
import com.zerobase.mission.customer.repository.CustomerRepository;
import com.zerobase.mission.reservation.dto.ArrivalForm;
import com.zerobase.mission.reservation.dto.CreateReservationForm;
import com.zerobase.mission.reservation.dto.ReservationDto;
import com.zerobase.mission.reservation.entity.Reservation;
import com.zerobase.mission.reservation.repository.ReservationRepository;
import com.zerobase.mission.reservation.type.ArrivalStatusType;
import com.zerobase.mission.reservation.type.ReservationStatusType;
import com.zerobase.mission.store.entity.Store;
import com.zerobase.mission.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    // 신규 예약 생성
    @Transactional
    public ReservationDto createReservation(CreateReservationForm form) {
        Store store = storeRepository.findById(form.getStoreId())
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        Customer customer = customerRepository.findById(form.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));

        if (reservationRepository.existsByReservationTime(form.getReservationDate(), form.getReservationTime())) {
            throw new CustomException(ErrorCode.ALREADY_RESERVED);
        }

        Reservation reservation = reservationRepository.save(Reservation.builder()
                        .customer(customer)
                        .store(store)
                        .reservationStatusType(ReservationStatusType.STANDBY)
                        .arrivalStatusType(ArrivalStatusType.READY)
                        .reservationDate(form.getReservationDate())
                        .reservationTime(form.getReservationTime())
                        .build());

        return ReservationDto.from(reservation);
    }

    // 예약 목록 조회
    public List<ReservationDto> getReservationList(Long managerId) {
        List<Reservation> reservationList = reservationRepository.findAllByManagerId(managerId);

        if (reservationList.isEmpty()) {
            throw new CustomException(ErrorCode.RESERVATION_NOT_FOUND);
        }

        return reservationList.stream().map(ReservationDto::from).collect(Collectors.toList());
    }

    // 매니저 예약 승인
    @Transactional
    public ReservationDto approvalReservationStatus(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

        ReservationStatusType statusType = reservation.getReservationStatusType();

        if (statusType.equals(ReservationStatusType.APPROVAL)) {
            throw new CustomException(ErrorCode.RESERVATION_STATUS_EQUAL);
        }

        reservation.setReservationStatusType(ReservationStatusType.APPROVAL);

        return ReservationDto.from(reservationRepository.save(reservation));
    }

    // 매니저 예약 거절
    @Transactional
    public void denyReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

        reservationRepository.delete(reservation);
    }


    // 도착상태 변경 및 매장 사용 상태 변경
    @Transactional
    public ReservationDto updateArrivalStatus(Long reservationId, ArrivalForm form) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

        if (!reservation.getReservationStatusType().equals(ReservationStatusType.APPROVAL)) {
            throw new CustomException(ErrorCode.RESERVATION_STATUS_ERROR);
        } else if(form.getArrivalTime().isAfter(LocalDateTime.of(reservation.getReservationDate(),reservation.getReservationTime()))) {
            throw new CustomException(ErrorCode.RESERVATION_NOT_VALID);
        } else if(!form.getArrivalTime().isBefore(LocalDateTime.of(reservation.getReservationDate(),reservation.getReservationTime()).minusMinutes(10L))) {
            throw new CustomException(ErrorCode.RESERVATION_CHECK_10_MINUTES);
        }

        reservation.setArrivalStatusType(ArrivalStatusType.ARRIVED);
        reservation.setReservationStatusType(ReservationStatusType.USE_COMPLETE);

        return ReservationDto.from(reservationRepository.save(reservation));
    }

    // 사용자가 예약 취소
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

        reservationRepository.delete(reservation);
    }


}
