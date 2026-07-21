package com.Ecommerce.Order.Controller;

import com.Ecommerce.Order.DTO.OrderRequestDTO;
import com.Ecommerce.Order.DTO.OrderResponse;
import com.Ecommerce.Order.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequestDTO orderRequestDTO,
            @RequestHeader("User-Id") UUID userId) {


        OrderResponse response =
                orderService.createOrder(orderRequestDTO, userId);


        return ResponseEntity.ok(response);
    }


}
