package com.sid.gl.service;

import com.sid.gl.dto.InventoryResponse;
import com.sid.gl.dto.OrderRequest;
import com.sid.gl.mapper.OrderMapper;
import com.sid.gl.model.Order;
import com.sid.gl.model.OrderLineItems;
import com.sid.gl.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    private static final String ORDER_URI="http://inventory-service/api/inventory";
    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
      List<OrderLineItems> orderLineItems= orderRequest.getOrderLineItemsDTOS()
                .stream()
                .map(OrderMapper::convertToOrderLineItems)
                .toList();

        order.setOrderLineItems(orderLineItems);
        List<String> skuCodeList = getAllSkucodes(orderLineItems);

        //TODO call inventory service to verify product on inventory
        InventoryResponse[] inventoryResponses = verifyOrderInInventory(skuCodeList);

//        if(inventoryResponses.length > 0)
//           orderRepository.save(order);
//        else
           //throw new IllegalArgumentException("product not available on stock");

        //another way
        boolean checkProductAllStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if(checkProductAllStock){
            orderRepository.save(order);
            return "order placed sucessfully !";
        }
        else{
            log.error("Product with name sku not found ");
            throw new IllegalArgumentException("product not available on stock");
        }


    }

    //create list recap all skucode on orderlineItems
    public List<String> getAllSkucodes(List<OrderLineItems> orderLineItems){
        return orderLineItems.stream()
                .map(OrderLineItems::getSkucode)
                .toList();
    }

    private InventoryResponse[] verifyOrderInInventory(List<String> skuCodeList){
        return webClientBuilder.build().get()
                .uri(ORDER_URI,
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
    }
}

