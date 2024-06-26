package com.gmail.shop.ecommerce.dto.response;

import com.gmail.shop.ecommerce.domain.Order;
import com.gmail.shop.ecommerce.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private User user;
    private Page<Order> orders;
}
