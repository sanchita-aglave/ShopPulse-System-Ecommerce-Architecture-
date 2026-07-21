package com.Ecommerce.Order.DTO;

import com.Ecommerce.Order.Enums.PaymentMethod;
import com.Ecommerce.Order.Enums.PaymentStatus;
import com.Ecommerce.Order.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;

    private String orderNumber;

    private BigDecimal totalAmount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private Status orderStatus;

    private String shippingAddress;

    private String orderNote;

    private LocalDateTime orderDate;

    private List<OrderItemResponse> orderItems;
}
