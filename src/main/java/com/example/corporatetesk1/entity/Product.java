package com.example.corporatetesk1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reviewCount", nullable = false)
    private Long reviewCount;

    @Column(name = "score", nullable = false)
    private Float score;
}
