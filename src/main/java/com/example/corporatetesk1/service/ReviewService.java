package com.example.corporatetesk1.service;

import com.example.corporatetesk1.dto.ReviewRequestDto;
import com.example.corporatetesk1.dto.ReviewResponseDto;
import com.example.corporatetesk1.entity.Product;
import com.example.corporatetesk1.entity.Review;
import com.example.corporatetesk1.entity.ReviewInfo;
import com.example.corporatetesk1.exception.ErrorCode;
import com.example.corporatetesk1.exception.ReviewApplicationException;
import com.example.corporatetesk1.repository.ProductRepository;
import com.example.corporatetesk1.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ReviewInfo getReviews(Long productId, Long cursor, Integer size) {
        // 먼저 해당 포스트가 있는지 확인
        Product product = getProductEntityOrException(productId);
        // 있으면 해당 포스트에서 지정 커서부터 지정된 사이즈(개수)만큼 리뷰 조회
        List<Review> reviews = reviewRepository.findAllByProduct(product, cursor, size);
        List<ReviewResponseDto> reviewList = new ArrayList<>();
        for (Review review : reviews) {
            reviewList.add(ReviewResponseDto.fromEntity(review));
        }

        return new ReviewInfo(product.getReviewCount(), product.getScore(), cursor, reviewList);
    }

    @Transactional
    public void postReview(Long productId, ReviewRequestDto reviewRequestDto, MultipartFile file) {
        // 상품이 있는지 먼저 확인하고 다음 로직 실행
        Product product = getProductEntityOrException(productId);
        // 해당 상품에 유저가 후기를 남겼는지 확인하고 다음 로직 실행
        Long userId = reviewRepository.findReviewById(productId, reviewRequestDto.getUserId());
        if (userId != null) throw new ReviewApplicationException(ErrorCode.ALREADY_REVIEWED);
        // 이미지의 유뮤에 따라 리뷰 저장
        if (file != null) reviewRepository.save(new Review(product, reviewRequestDto, file.getOriginalFilename()));
        else reviewRepository.save(new Review(product, reviewRequestDto));
        // 후기가 저장된 이후 후기의 개수 증가
        Long reviewCount = product.getReviewCount() + 1;
        // 후기가 저장된 이후 후기의 평점 계산
        product.setScore((product.getScore() * product.getReviewCount() + reviewRequestDto.getScore()) / reviewCount);
        product.setReviewCount(reviewCount);
    }

    // 상품이 있는지 확인
    private Product getProductEntityOrException(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ReviewApplicationException(ErrorCode.PRODUCT_NOT_FOUND));
    }
}
