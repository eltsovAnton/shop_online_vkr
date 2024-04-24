package com.gmail.shop.ecommerce.service.impl;

import com.gmail.shop.ecommerce.domain.Clothe;
import com.gmail.shop.ecommerce.domain.User;
import com.gmail.shop.ecommerce.repository.ClotheRepository;
import com.gmail.shop.ecommerce.service.CartService;
import com.gmail.shop.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final ClotheRepository clotheRepository;

    @Override
    public List<Clothe> getClothesInCart() {
        User user = userService.getAuthenticatedUser();
        System.out.printf("clothe:" + user.getEmail());
        return user.getClotheList();
    }

    @Override
    @Transactional
    public void addClotheToCart(Long clotheId) {
        User user = userService.getAuthenticatedUser();
        Clothe clothe = clotheRepository.getOne(clotheId);
        user.getClotheList().add(clothe);
    }

    @Override
    @Transactional
    public void removeClotheFromCart(Long clotheId) {
        User user = userService.getAuthenticatedUser();
        Clothe clothe = clotheRepository.getOne(clotheId);
        user.getClotheList().remove(clothe);
    }
}
