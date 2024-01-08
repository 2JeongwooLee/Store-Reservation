package com.zerobase.mission.review.dto;

import com.zerobase.mission.review.entity.Review;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String customerName;
    private String storeName;
    private double score;
    private String comment;

    public static ReviewDto from(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getId())
                .customerName(review.getCustomer().getName())
                .storeName(review.getStore().getStoreName())
                .score(review.getScore())
                .comment(review.getComment())
                .build();
    }

}
