package com.example.corporatetesk1.event;

// 이벤트 객체
public class ReviewPostedEvent {
    private final Long productId;
    private final float score;

    public ReviewPostedEvent(Long productId, float score) {
        this.productId = productId;
        this.score = score;
    }

    public Long getProductId() {
        return productId;
    }

    public float getScore() {
        return score;
    }
}
