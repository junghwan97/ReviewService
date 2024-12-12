package com.example.corporatetesk1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private Long userId;
    private float score;
    private String content;
}
