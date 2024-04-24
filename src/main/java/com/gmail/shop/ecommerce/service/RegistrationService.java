package com.gmail.shop.ecommerce.service;

import com.gmail.shop.ecommerce.dto.response.MessageResponse;
import com.gmail.shop.ecommerce.dto.request.UserRequest;

public interface RegistrationService {

    MessageResponse registration(String captchaResponse, UserRequest user);

    MessageResponse activateEmailCode(String code);
}
