package com.example.corporatetesk1.entity;

import com.example.corporatetesk1.dto.ReviewResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewInfo {

    private Long totalCount;
    private float score;
    private Long cursor;
    private List<ReviewResponseDto> reviews;

    public static ReviewInfo of(Long totalCount, float score, Long cursor, List<ReviewResponseDto> reviews) {
        return new ReviewInfo(totalCount, score, cursor, reviews);
    }
}
