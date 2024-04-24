package com.gmail.shop.ecommerce.service;

import com.gmail.shop.ecommerce.dto.request.PasswordResetRequest;
import com.gmail.shop.ecommerce.dto.response.MessageResponse;

public interface AuthenticationService {

    MessageResponse sendPasswordResetCode(String email);

    String getEmailByPasswordResetCode(String code);

    MessageResponse resetPassword(PasswordResetRequest request);
}
