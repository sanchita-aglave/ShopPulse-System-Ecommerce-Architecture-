package com.Ecommerce.Order.Entity;

import com.Ecommerce.Order.Enums.PaymentMethod;
import com.Ecommerce.Order.Enums.Status;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false,unique = true)
    private String orderNumber;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal discountAmount;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal tax;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status paymentStatus;

    @Column(nullable = false)
    private String shippingAddress;

    @Column(length = 500)
    private String orderNote;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderItems> orderItems;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (status == null) {
            status = Status.PENDING;
        }

        if (paymentStatus == null) {
            paymentStatus = Status.PENDING;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }






}
