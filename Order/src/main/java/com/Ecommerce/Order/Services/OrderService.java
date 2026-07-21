package com.Ecommerce.Order.Services;

import com.Ecommerce.Order.Client.ProductServiceClient;
import com.Ecommerce.Order.DTO.OrderItemDTO;
import com.Ecommerce.Order.DTO.OrderRequestDTO;
import com.Ecommerce.Order.DTO.OrderResponse;
import com.Ecommerce.Order.DTO.ProductResponse;
import com.Ecommerce.Order.Entity.Order;
import com.Ecommerce.Order.Entity.OrderItems;
import com.Ecommerce.Order.Enums.Status;
import com.Ecommerce.Order.Mapper.OrderMapper;
import com.Ecommerce.Order.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderMapper orderMapper)
    {
        this.orderMapper=orderMapper;
    }
    public OrderResponse createOrder(OrderRequestDTO orderRequestDTO, UUID userId)
    {

        Order order=orderMapper.mapToOrder(orderRequestDTO);
         order.setUserId(userId);
         order.setOrderNumber("ORD-" + System.currentTimeMillis());
         List<OrderItems> orderItems=new ArrayList<>();

        BigDecimal itemsTotal = BigDecimal.ZERO;

        for(OrderItemDTO itemDTO:orderRequestDTO.getOrderItems())
        {
            Long productId=itemDTO.getProductId();
            ProductResponse productResponse= productServiceClient.getProduct(productId);
            BigDecimal subtotal = productResponse.getFinalPrice()
                    .multiply(BigDecimal.valueOf(itemDTO.getQuantity()));

            OrderItems orderItem = OrderItems.builder()
                    .productId(productResponse.getProductId())
                    .productName(productResponse.getProductName())
                    .quantity(itemDTO.getQuantity())
                    .unitPrice(productResponse.getPrice())
                    .discountPercentage(
                            productResponse.getDiscountPercentage().intValue()
                    )
                    .finalPrice(productResponse.getFinalPrice())
                    .subtotal(subtotal)
                    .order(order)
                    .build();


            orderItems.add(orderItem);


            // Add item subtotal
            itemsTotal = itemsTotal.add(subtotal);


        }

        order.setOrderItems(orderItems);

        order.setDiscountAmount(BigDecimal.ZERO);
        BigDecimal taxRate = new BigDecimal("0.18");

        BigDecimal tax = itemsTotal.multiply(taxRate);

        order.setTax(tax);

        order.setExpectedDeliveryDate(LocalDate.now().plusDays(5));
        if (order.getStatus() == Status.DELIVERED) {
            order.setDeliveredAt(LocalDateTime.now());
        }

        BigDecimal deliveryCharge;


        if(itemsTotal.compareTo(new BigDecimal("500")) >= 0) {

            // Free delivery above 500
            deliveryCharge = BigDecimal.ZERO;

        } else {

            deliveryCharge = new BigDecimal("50");
        }


        order.setDeliveryCharge(deliveryCharge);

        BigDecimal totalAmount =
                itemsTotal
                        .subtract(order.getDiscountAmount())
                        .add(tax)
                        .add(deliveryCharge);


        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);



        // Convert entity to response
        OrderResponse response =
                orderMapper.mapToOrderResponse(savedOrder);


        return response;
    }
}
