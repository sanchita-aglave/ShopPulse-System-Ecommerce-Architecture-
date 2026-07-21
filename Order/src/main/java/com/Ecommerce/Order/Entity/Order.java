package com.Ecommerce.Order.Entity;

import com.Ecommerce.Order.Enums.PaymentMethod;
import com.Ecommerce.Order.Enums.PaymentStatus;
import com.Ecommerce.Order.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false,unique = true)
    private String orderNumber;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal discountAmount;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal tax;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal deliveryCharge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    private String shippingAddress;

    @Column(length = 500)
    private String orderNote;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDate expectedDeliveryDate;
    private LocalDateTime deliveredAt;

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
            paymentStatus = PaymentStatus.PENDING;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
