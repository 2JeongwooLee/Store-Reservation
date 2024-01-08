package com.zerobase.mission.review.repository;

import com.zerobase.mission.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByReservationId(Long reservationId);

    @Query("select r from Review r where r.store.id = :storeId")
    List<Review> findAllByStoreId(@Param("storeId") Long storeId);
}
