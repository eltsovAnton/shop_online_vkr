package com.gmail.shop.ecommerce.service;

import com.gmail.shop.ecommerce.domain.Order;
import com.gmail.shop.ecommerce.domain.Clothe;
import com.gmail.shop.ecommerce.domain.User;
import com.gmail.shop.ecommerce.dto.request.ClotheRequest;
import com.gmail.shop.ecommerce.dto.request.SearchRequest;
import com.gmail.shop.ecommerce.dto.response.MessageResponse;
import com.gmail.shop.ecommerce.dto.response.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    Page<Clothe> getClothes(Pageable pageable);

    Page<Clothe> searchClothes(SearchRequest request, Pageable pageable);

    Page<User> getUsers(Pageable pageable);

    Page<User> searchUsers(SearchRequest request, Pageable pageable);

    Order getOrder(Long orderId);

    Page<Order> getOrders(Pageable pageable);

    Page<Order> searchOrders(SearchRequest request, Pageable pageable);

    Clothe getClotheById(Long clotheId);

    MessageResponse editClothe(ClotheRequest clotheRequest, MultipartFile file);

    MessageResponse addClothe(ClotheRequest clotheRequest, MultipartFile file);

    UserInfoResponse getUserById(Long userId, Pageable pageable);
}
