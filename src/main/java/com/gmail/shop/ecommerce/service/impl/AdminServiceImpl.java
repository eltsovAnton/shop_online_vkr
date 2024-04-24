package com.gmail.shop.ecommerce.service.impl;

import com.gmail.shop.ecommerce.constants.ErrorMessage;
import com.gmail.shop.ecommerce.constants.SuccessMessage;
import com.gmail.shop.ecommerce.domain.Clothe;
import com.gmail.shop.ecommerce.domain.Order;
import com.gmail.shop.ecommerce.domain.User;
import com.gmail.shop.ecommerce.dto.request.ClotheRequest;
import com.gmail.shop.ecommerce.dto.request.SearchRequest;
import com.gmail.shop.ecommerce.dto.response.MessageResponse;
import com.gmail.shop.ecommerce.dto.response.UserInfoResponse;
import com.gmail.shop.ecommerce.repository.OrderRepository;
import com.gmail.shop.ecommerce.repository.ClotheRepository;
import com.gmail.shop.ecommerce.repository.UserRepository;
import com.gmail.shop.ecommerce.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserRepository userRepository;
    private final ClotheRepository clotheRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<Clothe> getClothes(Pageable pageable) {
        return clotheRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Clothe> searchClothes(SearchRequest request, Pageable pageable) {
        return clotheRepository.searchClothes(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> searchUsers(SearchRequest request, Pageable pageable) {
        return userRepository.searchUsers(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.getById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);

    }

    @Override
    public Page<Order> searchOrders(SearchRequest request, Pageable pageable) {
        return orderRepository.searchOrders(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Clothe getClotheById(Long clotheId) {
        return clotheRepository.findById(clotheId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.CLOTHE_NOT_FOUND));
    }

    @Override
    @SneakyThrows
    @Transactional
    public MessageResponse editClothe(ClotheRequest clotheRequest, MultipartFile file) {
        return saveClothe(clotheRequest, file, SuccessMessage.CLOTHE_EDITED);
    }

    @Override
    @SneakyThrows
    @Transactional
    public MessageResponse addClothe(ClotheRequest clotheRequest, MultipartFile file) {
        return saveClothe(clotheRequest, file, SuccessMessage.CLOTHE_ADDED);
    }

    @Override
    public UserInfoResponse getUserById(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND));
        Page<Order> orders = orderRepository.findOrderByUserId(userId, pageable);
        return new UserInfoResponse(user, orders);
    }

    private MessageResponse saveClothe(ClotheRequest clotheRequest, MultipartFile file, String message) throws IOException {
        Clothe clothe = modelMapper.map(clotheRequest, Clothe.class);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String absolutePath = uploadDir.getAbsolutePath() + File.separator + resultFilename;

            file.transferTo(new File(absolutePath));
            clothe.setFilename(resultFilename);
        }
        clotheRepository.save(clothe);
        return new MessageResponse("alert-success", message);
    }

}
