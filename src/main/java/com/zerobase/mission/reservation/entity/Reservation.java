package com.zerobase.mission.reservation.entity;

import com.zerobase.mission.common.entity.BaseEntity;
import com.zerobase.mission.customer.entity.Customer;
import com.zerobase.mission.reservation.type.ArrivalStatusType;
import com.zerobase.mission.reservation.type.ReservationStatusType;
import com.zerobase.mission.store.entity.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private ReservationStatusType reservationStatusType;

    @Enumerated(EnumType.STRING)
    private ArrivalStatusType arrivalStatusType;

    @Column
    private LocalDate reservationDate;

    @Column
    private LocalTime reservationTime;
}
