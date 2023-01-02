package com.sid.gl.controller;

import com.sid.gl.dto.OrderRequest;
import com.sid.gl.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String>  addOrder(@RequestBody OrderRequest orderRequest){
       return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest)) ;
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException exception){
        return CompletableFuture.supplyAsync(()->"OOps! something is wrong !! Tyr later order") ;
    }
}
