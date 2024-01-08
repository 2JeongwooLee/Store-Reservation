package com.zerobase.mission.store.entity;

import com.zerobase.mission.common.entity.BaseEntity;
import com.zerobase.mission.manager.entity.Manager;
import com.zerobase.mission.reservation.entity.Reservation;
import com.zerobase.mission.review.entity.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @Column
    private String storeName;

    @Column
    private String location;

    @Column
    private String phoneNumber;

    @Column
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservationList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

}
