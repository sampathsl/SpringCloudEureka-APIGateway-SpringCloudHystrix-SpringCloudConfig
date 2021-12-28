package io.sampathlk.service.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sampathlk.service.order.common.OrderResponse;
import io.sampathlk.service.order.common.Payment;
import io.sampathlk.service.order.entity.Order;
import io.sampathlk.service.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@RefreshScope
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy
    private RestTemplate template;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String endPointURI;

    public OrderResponse saveOrder(Order order, Payment payment) throws JsonProcessingException {
        String paymentSuccessful = "Payment processing successful and order placed";
        String paymentFailed = "There is a failure in payment api , order added to the cart";
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        Payment paymentResponse = template.postForObject(endPointURI, payment, Payment.class);
        String response = paymentResponse.getPaymentStatus().equals("success") ? paymentSuccessful : paymentFailed;
        logger.info("Order Service getting Response from Payment-Service : " + new ObjectMapper().writeValueAsString(response));
        repository.save(order);
        return new OrderResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }

}
