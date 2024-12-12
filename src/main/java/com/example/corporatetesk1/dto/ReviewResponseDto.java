package com.example.corporatetesk1.dto;

import com.example.corporatetesk1.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class ReviewResponseDto {

    private Long id;
    private Long userId;
    private float score;
    private String content;
    private String imageUrl;
    private Timestamp createdAt;

    public static ReviewResponseDto fromEntity(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getUserId(),
                review.getScore(),
                review.getContent(),
                review.getImageUrl(),
                review.getCreatedAt()
        );
    }
}
