package com.gmail.shop.ecommerce.service;

import com.gmail.shop.ecommerce.domain.Clothe;

import java.util.List;

public interface CartService {

    List<Clothe> getClothesInCart();

    void addClotheToCart(Long clotheId);

    void removeClotheFromCart(Long perfumeId);
}
