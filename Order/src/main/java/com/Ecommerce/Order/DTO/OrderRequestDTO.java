package com.Ecommerce.Order.DTO;

import com.Ecommerce.Order.Enums.PaymentMethod;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    @Enumerated
    @NotNull(message = "Payment Method is required")
    private PaymentMethod paymentMethod;

    @NotBlank(message = "Shipping Address is required")
    private String shippingAddress;

    private String orderNote;

    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemDTO> orderItems;


}
