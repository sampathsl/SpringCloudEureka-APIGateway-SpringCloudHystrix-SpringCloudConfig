package io.sampathlk.service.order.common;

import io.sampathlk.service.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Order order;
    private double amount;
    private String transactionId;
    private String message;
}
