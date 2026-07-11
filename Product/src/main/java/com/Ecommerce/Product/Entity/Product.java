package com.Ecommerce.Product.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(nullable = false)
    private String productName;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double price;

    private Double discountPercentage;

    private Double finalPrice;

    @Column(nullable = false)
   private Integer stockQuantity;

    private String brand;

    private String imageUrl;

    private Double averageRating;

    private Integer totalReviews;

    private Boolean active;

    private LocalDate createdAt;

    private LocalDate updateAt;

    @PrePersist
    public void prePersist()
    {
        createdAt=LocalDate.now();
        updateAt=LocalDate.now();
    }

    @PreUpdate
    public void preUpdate(){
        updateAt=LocalDate.now();
    }

    // From Auth Service
    private UUID sellerId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Inventory inventory;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Review> review;



}
