package com.example.corporatetesk1.listener;

import com.example.corporatetesk1.entity.Product;
import com.example.corporatetesk1.event.ReviewPostedEvent;
import com.example.corporatetesk1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewEventListener {

    private final ProductRepository productRepository;

    @Async // 비동기 처리
    @EventListener
    public void handleReviewPosted(ReviewPostedEvent event) {
        Product product = productRepository.findById(event.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // 리뷰 개수 +1
        long reviewCount = product.getReviewCount() + 1;
        // 리뷰 평점 다시 계산
        float newScore = (product.getScore() * product.getReviewCount() + event.getScore()) / reviewCount;

        product.setReviewCount(reviewCount);
        product.setScore(newScore);

        productRepository.save(product);
    }
}
