package com.mircoservice.order_service.service;

import com.mircoservice.order_service.dto.InventoryResponse;
import com.mircoservice.order_service.dto.OrderLineItemDto;
import com.mircoservice.order_service.dto.OrderRequest;
import com.mircoservice.order_service.model.Order;
import com.mircoservice.order_service.model.OrderLineItem;
import com.mircoservice.order_service.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepo orderRepo;
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest) {
        // Validate orderRequest and its items
        if (orderRequest == null || orderRequest.getOrderLineItemDtoList() == null || orderRequest.getOrderLineItemDtoList().isEmpty()) {
            throw new IllegalArgumentException("Order request or order items cannot be null/empty");
        }

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList().stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemList().stream()
                .map(OrderLineItem::getSkuCode)
                .toList();

        InventoryResponse[] result = webClient.get()
                .uri("http://localhost:8083/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(result)
                .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepo.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock");
        }
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
    OrderLineItem orderLineItem = new OrderLineItem();
    orderLineItem.setQuantity(orderLineItemDto.getQuantity());
    orderLineItem.setPrice(orderLineItemDto.getPrice());
    orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
    return orderLineItem;
    }
}
