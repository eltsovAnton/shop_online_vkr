package com.gmail.shop.ecommerce.controller;

import com.gmail.shop.ecommerce.constants.Pages;
import com.gmail.shop.ecommerce.constants.PathConstants;
import com.gmail.shop.ecommerce.dto.request.SearchRequest;
import com.gmail.shop.ecommerce.service.ClotheService;
import com.gmail.shop.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.CLOTHE)
public class ClotheController {

    private final ClotheService clotheService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/{clotheId}")
    public String getClotheById(@PathVariable Long clotheId, Model model) {
        model.addAttribute("clothe", clotheService.getClotheById(clotheId));
        System.out.printf("clothe id" + clotheId);
        return Pages.CLOTHE;
    }

    @GetMapping
    public String getClothesByFilterParams(SearchRequest request, Model model, Pageable pageable) {
        controllerUtils.addPagination(request, model, clotheService.getClothesByFilterParams(request, pageable));
        return Pages.CLOTHES;
    }

    @GetMapping("/search")
    public String searchClothes(SearchRequest request, Model model, Pageable pageable) {
        controllerUtils.addPagination(request, model, clotheService.searchClothes(request, pageable));
        return Pages.CLOTHES;
    }
}
