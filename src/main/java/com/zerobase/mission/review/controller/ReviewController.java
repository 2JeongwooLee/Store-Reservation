package com.zerobase.mission.review.controller;

import com.zerobase.mission.review.dto.ReviewForm;
import com.zerobase.mission.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 등록
    @PostMapping("/review/create")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> createReview(
            @RequestParam(name = "userid") Long userId,
            @RequestParam(name = "storeid") Long storeId,
            @RequestParam(name = "reservationid") Long reservationId,
            @RequestBody ReviewForm form
            ) {
        return ResponseEntity.ok().body(reviewService.createReview(userId, storeId, reservationId, form));
    }


    // 리뷰 조회
    @GetMapping("/review/{storeId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER')")
    public ResponseEntity<?> getReview(@PathVariable Long storeId) {
        return ResponseEntity.ok(reviewService.getReview(storeId));
    }


    // 리뷰 수정
    @PutMapping("/review/update/{reviewId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody ReviewForm form) {
        return ResponseEntity.ok().body(reviewService.updateReview(reviewId, form));
    }


    // 리뷰 삭제
    @DeleteMapping("/review/delete/{reviewId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER')")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("리뷰 삭제가 완료되었습니다.");
    }


}
