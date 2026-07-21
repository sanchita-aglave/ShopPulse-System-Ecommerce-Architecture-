package com.Ecommerce.Order.Mapper;


import com.Ecommerce.Order.DTO.OrderItemDTO;
import com.Ecommerce.Order.DTO.OrderItemResponse;
import com.Ecommerce.Order.Entity.OrderItems;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItems mapToOrderItemEntity(OrderItemDTO orderItemDTO)
    {
        return OrderItems.builder()
                .productId(orderItemDTO.getProductId())
                .quantity(orderItemDTO.getQuantity())
                .build();
    }

    public OrderItemResponse mapToOrderItemResponse(OrderItems orderItems)
    {
        return OrderItemResponse.builder()
                .productId(orderItems.getProductId())
                .productName(orderItems.getProductName())
                .quantity(orderItems.getQuantity())
                .unitPrice(orderItems.getUnitPrice())
                .finalPrice(orderItems.getFinalPrice())
                .subtotal(orderItems.getSubtotal())
                .build();
    }

}
