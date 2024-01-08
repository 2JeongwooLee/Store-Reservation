package com.zerobase.mission.review.service;

import com.zerobase.mission.common.exception.CustomException;
import com.zerobase.mission.common.exception.ErrorCode;
import com.zerobase.mission.customer.entity.Customer;
import com.zerobase.mission.customer.repository.CustomerRepository;
import com.zerobase.mission.reservation.entity.Reservation;
import com.zerobase.mission.reservation.repository.ReservationRepository;
import com.zerobase.mission.reservation.type.ReservationStatusType;
import com.zerobase.mission.review.dto.ReviewForm;
import com.zerobase.mission.review.dto.ReviewDto;
import com.zerobase.mission.review.entity.Review;
import com.zerobase.mission.review.repository.ReviewRepository;
import com.zerobase.mission.store.entity.Store;
import com.zerobase.mission.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    // 리뷰 등록
    @Transactional
    public ReviewDto createReview(Long customerId, Long storeId, Long reservationId, ReviewForm form) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

        if (!reservation.getCustomer().getId().equals(customer.getId())) {
            throw new CustomException(ErrorCode.CUSTOMER_NOT_MATCH);
        } else if (!reservation.getReservationStatusType().equals(ReservationStatusType.USE_COMPLETE)) {
            throw new CustomException(ErrorCode.REVIEW_NOT_ALLOWED);
        } else if (reviewRepository.existsByReservationId(reservation.getId())) {
            throw new CustomException(ErrorCode.ALREADY_EXISTED_REVIEW);
        } else if(form.getScore() < 0.0 || form.getScore() > 5.0) {
            throw new CustomException(ErrorCode.NOT_IN_RANGE_SCORE);
        } else if(form.getComment().length() > 200) {
            throw new CustomException(ErrorCode.TOO_LONG_COMMENT);
        }

        Review savedReview = reviewRepository.save(Review.builder()
                .customer(customer)
                .store(store)
                .reservation(reservation)
                .score(form.getScore())
                .comment(form.getComment())
                .build());

        return ReviewDto.from(savedReview);
    }

    // 해당 가게에 대한 리뷰 조회
    public List<ReviewDto> getReview(Long storeId) {
        List<Review> reviewList = reviewRepository.findAllByStoreId(storeId);

        if (reviewList.isEmpty()) {
            throw new CustomException(ErrorCode.REVIEW_NOT_FOUND);
        }

        return reviewList.stream().map(ReviewDto::from).collect(Collectors.toList());
    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(Long reviewId) {
        Review deletedReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

        reviewRepository.delete(deletedReview);
    }

    // 리뷰 수정
    @Transactional
    public ReviewDto updateReview(Long reviewId, ReviewForm form) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

        if(form.getScore() < 0.0 || form.getScore() > 5.0) {
            throw new CustomException(ErrorCode.NOT_IN_RANGE_SCORE);
        } else if(form.getComment().length() > 200) {
            throw new CustomException(ErrorCode.TOO_LONG_COMMENT);
        }

        review.setComment(form.getComment());
        review.setScore(form.getScore());

        Review savedReview = reviewRepository.save(review);

        return ReviewDto.from(savedReview);
    }
}
