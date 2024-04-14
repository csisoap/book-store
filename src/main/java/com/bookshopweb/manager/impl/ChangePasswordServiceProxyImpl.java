package com.bookshopweb.manager.impl;

import com.bookshopweb.beans.User;
import com.bookshopweb.dto.ChangePasswordRequest;
import com.bookshopweb.manager.ChangePasswordService;
import com.bookshopweb.utils.HashingUtils;
import com.bookshopweb.utils.Protector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// proxy pattern
public class ChangePasswordServiceProxyImpl implements ChangePasswordService {
    private HttpServletRequest httpServletRequest;
    private ChangePasswordService changePasswordService;

    public ChangePasswordServiceProxyImpl(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        this.changePasswordService = new ChangePasswordServiceImpl();
    }

    @Override
    public Protector<?> change(ChangePasswordRequest changePasswordRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("currentUser");

        if(!HashingUtils.hash(changePasswordRequest.getCurrentPassword()).equals(user.getPassword())) {
            return Protector.error(new Exception("Wrong username or password!"));
        }

        changePasswordRequest.setUserId(user.getId());
        return changePasswordService.change(changePasswordRequest);
    }
}
