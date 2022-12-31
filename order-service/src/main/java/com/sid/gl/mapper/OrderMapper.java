package com.sid.gl.mapper;

import com.sid.gl.dto.OrderLineItemsDTO;
import com.sid.gl.model.OrderLineItems;

public class OrderMapper {
    public static OrderLineItems convertToOrderLineItems(OrderLineItemsDTO orderLineItemsDTO){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setSkucode(orderLineItemsDTO.getSkucode());
        return orderLineItems;
    }
}
