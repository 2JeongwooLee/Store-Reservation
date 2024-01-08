package com.zerobase.mission.review.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewForm {

    private String comment;
    private double score;
}
