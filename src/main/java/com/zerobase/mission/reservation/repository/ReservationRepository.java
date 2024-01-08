package com.zerobase.mission.reservation.repository;

import com.zerobase.mission.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.reservationDate = :reservationDate AND r.reservationTime = :reservationTime")
    boolean existsByReservationTime(@Param("reservationDate") LocalDate reservationDate, @Param("reservationTime") LocalTime reservationTime);

    @Query("SELECT r FROM Reservation r WHERE r.store.manager.id = :id")
    List<Reservation> findAllByManagerId(@Param("id") Long id);
}
