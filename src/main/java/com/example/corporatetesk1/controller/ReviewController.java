package com.example.corporatetesk1.controller;

import com.example.corporatetesk1.dto.ReviewRequestDto;
import com.example.corporatetesk1.entity.ReviewInfo;
import com.example.corporatetesk1.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 조회하기
    @GetMapping("{productId}/reviews")
    public ReviewInfo getReviews(@PathVariable Long productId,
                                 @RequestParam(value = "cursor", defaultValue = "0") Long cursor,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return reviewService.getReviews(productId, cursor, size);
    }

    // 리뷰 등록하기
    @PostMapping("{productId}/reviews")
    public void postReview(@PathVariable Long productId, @RequestPart ReviewRequestDto reviewRequestDto, @RequestPart(name = "file", required = false) MultipartFile file) {
        reviewService.postReview(productId, reviewRequestDto, file);
    }
}
