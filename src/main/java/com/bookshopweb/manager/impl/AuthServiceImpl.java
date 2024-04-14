package com.bookshopweb.manager.impl;

import com.bookshopweb.beans.User;
import com.bookshopweb.consts.Enums;
import com.bookshopweb.dto.SignUpRequest;
import com.bookshopweb.instance.ServiceFactory;
import com.bookshopweb.manager.AuthService;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.HashingUtils;
import com.bookshopweb.utils.Protector;
import com.bookshopweb.validator.Validator;

public class AuthServiceImpl implements AuthService {
    private final Validator<SignUpRequest> validator;
    private final UserService userService = (UserService) ServiceFactory.getService(Enums.ServiceType.USER);

    public AuthServiceImpl(Validator<SignUpRequest> validator) {
        this.validator = validator;
    }

    @Override
    public Protector<?> signUp(SignUpRequest signUpRequest) {
        try {
            validator.validate(signUpRequest);

            User user = new User(
                    0L,
                    signUpRequest.getUsername(),
                    HashingUtils.hash(signUpRequest.getPassword()),
                    signUpRequest.getFullname(),
                    signUpRequest.getEmail(),
                    signUpRequest.getPhoneNumber(),
                    Protector.of(() -> Integer.parseInt(signUpRequest.getGender())).get(0),
                    signUpRequest.getAddress(),
                    "CUSTOMER"
            );
            return Protector.of(() -> userService.insert(user));
        } catch (Exception e) {
            return Protector.error(e);
        }
    }



}
