package io.sampathlk.service.order.controller;

import io.sampathlk.service.order.common.OrderRequest;
import io.sampathlk.service.order.common.OrderResponse;
import io.sampathlk.service.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/book")
    public OrderResponse bookOrder(@RequestBody OrderRequest req) {
        try {
            return orderService.saveOrder(req.getOrder(), req.getPayment());
        } catch (Exception exception) {
            logger.error("{}", exception.getMessage());
        }
        // TODO - Need to handle error
        return new OrderResponse();
    }

}
