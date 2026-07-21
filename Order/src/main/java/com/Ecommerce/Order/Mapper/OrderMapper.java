package com.Ecommerce.Order.Mapper;

import com.Ecommerce.Order.DTO.OrderRequestDTO;
import com.Ecommerce.Order.DTO.OrderResponse;
import com.Ecommerce.Order.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    public static Order mapToOrder(OrderRequestDTO orderRequestDTO)
    {
        return Order.builder()
                .paymentMethod(orderRequestDTO.getPaymentMethod())
                .shippingAddress(orderRequestDTO.getShippingAddress())
                .orderNote(orderRequestDTO.getOrderNote())
                .build();
    }

    public OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderNumber(order.getOrderNumber())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .orderStatus(order.getStatus())
                .shippingAddress(order.getShippingAddress())
                .orderNote(order.getOrderNote())
                .orderDate(order.getCreatedAt())
                .orderItems(
                        order.getOrderItems()
                                .stream()
                                .map(orderItemMapper::mapToOrderItemResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }


}
