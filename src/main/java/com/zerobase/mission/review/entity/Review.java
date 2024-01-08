package com.zerobase.mission.review.entity;

import com.zerobase.mission.common.entity.BaseEntity;
import com.zerobase.mission.customer.entity.Customer;
import com.zerobase.mission.reservation.entity.Reservation;
import com.zerobase.mission.store.entity.Store;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(precision = 2, scale = 1)
    @Digits(integer = 1, fraction = 1)
    private double score;

    @Column(length = 200)
    private String comment;
}
