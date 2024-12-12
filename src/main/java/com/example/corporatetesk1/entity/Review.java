package com.example.corporatetesk1.entity;

import com.example.corporatetesk1.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "Review")
@Getter
@Setter
@SQLDelete(sql = "UPDATE post SET deleted_at = NOW() where id=?")
@SQLRestriction("deleted_at is NULL")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "score", nullable = false)
    private float score;

    @Column(name = "image")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    public Review() {

    }

    public Review(Product product, ReviewRequestDto requestDto, String file) {
        this.userId = requestDto.getUserId();
        this.content = requestDto.getContent();
        this.score = requestDto.getScore();
        this.imageUrl = file;
        this.product = product;
    }

    public Review(Product product, ReviewRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.content = requestDto.getContent();
        this.score = requestDto.getScore();
        this.product = product;
    }
}
