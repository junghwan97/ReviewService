package com.example.corporatetesk1.repository;

import com.example.corporatetesk1.entity.Product;
import com.example.corporatetesk1.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r " +
            "JOIN Product p ON p.id = r.product.id " +
            "WHERE r.product = :product AND p.id < :cursor " +
            "ORDER BY r.id DESC " +
            "LIMIT :size ")
    List<Review> findAllByProduct(Product product, Long cursor, Integer size);

    @Query("SELECT userId FROM Review " +
            "WHERE product.id = :productId " +
            "AND userId = :userId ")
    Long findReviewById(Long productId, Long userId);

}
