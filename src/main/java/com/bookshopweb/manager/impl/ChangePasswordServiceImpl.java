package com.bookshopweb.manager.impl;

import com.bookshopweb.consts.Enums;
import com.bookshopweb.dto.ChangePasswordRequest;
import com.bookshopweb.instance.ServiceFactory;
import com.bookshopweb.manager.ChangePasswordService;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.HashingUtils;
import com.bookshopweb.utils.Protector;

public class ChangePasswordServiceImpl implements ChangePasswordService {
    private final UserService userService = (UserService) ServiceFactory.getService(Enums.ServiceType.USER);

    @Override
    public Protector<?> change(ChangePasswordRequest changePasswordRequest) {
        boolean newPasswordEqualsNewPasswordAgain = changePasswordRequest.getNewPassword().equals(changePasswordRequest.getNewPasswordAgain());

        if (newPasswordEqualsNewPasswordAgain) {
            String newPassword = HashingUtils.hash(changePasswordRequest.getNewPassword());
            userService.changePassword(changePasswordRequest.getUserId(), newPassword);
            return Protector.of(() -> true);
        } else {
            return Protector.error(new Exception("Password must be same!"));
        }
    }
}
