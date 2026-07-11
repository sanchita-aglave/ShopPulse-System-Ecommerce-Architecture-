package com.ecommerce.API_Gateway.Filter;

import com.ecommerce.API_Gateway.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class AuthFilter implements  GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path=exchange.getRequest().getURI().getPath();

        //Allow Login and Registration without JWT
        if(path.startsWith("/Auth"))
        {
            return chain.filter(exchange);
        }

        String authHeader=exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        System.out.println("Header: " + authHeader);

        if(authHeader==null || !authHeader.startsWith("Bearer "))
        {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token=authHeader.substring(7);
        System.out.println("Token: " + token);
        System.out.println("Token Valid: " + jwtUtil.ValidateToken(token));
        if(!jwtUtil.ValidateToken(token))
        {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Extract Claims
        UUID userId = jwtUtil.extractUserId(token);
        String email = jwtUtil.extractEmail(token);
        String role = jwtUtil.extractRole(token);

        // Add user details as headers
        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header("User-Id", userId.toString())
                .header("User-Email", email)
                .header("User-Role", role)
                .build();

        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(request)
                .build();

        return chain.filter(modifiedExchange);
    }
}
