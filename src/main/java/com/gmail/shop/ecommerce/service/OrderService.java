package com.gmail.shop.ecommerce.service;

import com.gmail.shop.ecommerce.domain.Clothe;
import com.gmail.shop.ecommerce.domain.Order;
import com.gmail.shop.ecommerce.domain.User;
import com.gmail.shop.ecommerce.dto.request.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Order getOrder(Long orderId);

    List<Clothe> getOrdering();

    Page<Order> getUserOrdersList(Pageable pageable);

    Long postOrder(User user, OrderRequest orderRequest);
}
