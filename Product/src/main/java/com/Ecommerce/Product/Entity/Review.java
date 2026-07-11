package com.Ecommerce.Product.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReviewId;

    private Integer rating;

    private String comment;

    // Comes from JWT
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}
