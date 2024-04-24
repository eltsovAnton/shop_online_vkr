package com.gmail.shop.ecommerce.controller;

import com.gmail.shop.ecommerce.constants.Pages;
import com.gmail.shop.ecommerce.domain.Clothe;
import com.gmail.shop.ecommerce.service.ClotheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ClotheService clotheService;

    @GetMapping
    public String home(Model model) {
        List<Clothe> clothes = clotheService.getPopularClothes();
//        System.out.println("Number of clothes: " + clothes.size()); // Временный вывод в консоль
        model.addAttribute("clothes", clothes);
        return Pages.HOME;
    }
}
