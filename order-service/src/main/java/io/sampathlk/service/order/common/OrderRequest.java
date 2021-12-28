package io.sampathlk.service.order.common;

import io.sampathlk.service.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Order order;
    private Payment payment;
}
