package com.gmail.shop.ecommerce.service;

import com.gmail.shop.ecommerce.domain.Order;
import com.gmail.shop.ecommerce.domain.User;
import com.gmail.shop.ecommerce.dto.request.ChangePasswordRequest;
import com.gmail.shop.ecommerce.dto.request.EditUserRequest;
import com.gmail.shop.ecommerce.dto.request.SearchRequest;
import com.gmail.shop.ecommerce.dto.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getAuthenticatedUser();

    Page<Order> searchUserOrders(SearchRequest request, Pageable pageable);

    MessageResponse editUserInfo(EditUserRequest request);

    MessageResponse changePassword(ChangePasswordRequest request);
}
