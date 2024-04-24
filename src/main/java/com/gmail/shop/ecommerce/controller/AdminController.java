package com.gmail.shop.ecommerce.controller;

import com.gmail.shop.ecommerce.constants.Pages;
import com.gmail.shop.ecommerce.constants.PathConstants;
import com.gmail.shop.ecommerce.dto.request.ClotheRequest;
import com.gmail.shop.ecommerce.dto.request.SearchRequest;
import com.gmail.shop.ecommerce.dto.response.UserInfoResponse;
import com.gmail.shop.ecommerce.service.AdminService;
import com.gmail.shop.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(PathConstants.ADMIN)
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/clothes")
    public String getClothes(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getClothes(pageable));
        return Pages.ADMIN_CLOTHES;
    }

    @GetMapping("/clothes/search")
    public String searchClothe(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchClothes(request, pageable));
        return Pages.ADMIN_CLOTHES;
    }

    @GetMapping("/users")
    public String getUsers(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getUsers(pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/users/search")
    public String searchUsers(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchUsers(request, pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", adminService.getOrder(orderId));
        return Pages.ORDER;
    }

    @GetMapping("/orders")
    public String getOrders(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getOrders(pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/orders/search")
    public String searchOrders(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchOrders(request, pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/clothe/{clotheId}")
    public String getClothe(@PathVariable Long clotheId, Model model) {
        model.addAttribute("clothe", adminService.getClotheById(clotheId));
        return Pages.ADMIN_EDIT_CLOTHE;
    }

    @PostMapping("/edit/clothe")
    public String editClothe(@Valid ClotheRequest clothe, BindingResult bindingResult, Model model,
                              @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "clothe", clothe)) {
            return Pages.ADMIN_EDIT_CLOTHE;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/clothes", adminService.editClothe(clothe, file));
    }

    @GetMapping("/add/clothe")
    public String addClothe() {
        return Pages.ADMIN_ADD_CLOTHE;
    }

    @PostMapping("/add/clothe")
    public String addClothe(@Valid ClotheRequest clothe, BindingResult bindingResult, Model model,
                             @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "clothe", clothe)) {
            return Pages.ADMIN_ADD_CLOTHE;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/clothes", adminService.addClothe(clothe, file));
    }

    @GetMapping("/user/{userId}")
    public String getUserById(@PathVariable Long userId, Model model, Pageable pageable) {
        UserInfoResponse userResponse = adminService.getUserById(userId, pageable);
        model.addAttribute("user", userResponse.getUser());
        controllerUtils.addPagination(model, userResponse.getOrders());
        return Pages.ADMIN_USER_DETAIL;
    }
}
