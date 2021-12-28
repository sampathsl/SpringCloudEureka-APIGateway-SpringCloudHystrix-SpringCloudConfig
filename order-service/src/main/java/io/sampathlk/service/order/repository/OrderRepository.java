package io.sampathlk.service.order.repository;

import io.sampathlk.service.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
