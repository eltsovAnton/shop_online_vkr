package com.gmail.shop.ecommerce.controller;

import com.gmail.shop.ecommerce.constants.Pages;
import com.gmail.shop.ecommerce.constants.PathConstants;
import com.gmail.shop.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.CART)
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("clothes", cartService.getClothesInCart());
        return Pages.CART;
    }

    @PostMapping("/add")
    public String addClotheToCart(@RequestParam("clotheId") Long clotheId) {
        cartService.addClotheToCart(clotheId);
        System.out.printf("clotheID" + clotheId);
        return "redirect:" + PathConstants.CART;
    }

    @PostMapping("/remove")
    public String removeClotheFromCart(@RequestParam("clotheId") Long clotheId) {
        cartService.removeClotheFromCart(clotheId);
        return "redirect:" + PathConstants.CART;
    }
}
