package com.bookshopweb.manager;

import com.bookshopweb.dto.ChangePasswordRequest;
import com.bookshopweb.utils.Protector;

import javax.servlet.http.HttpServletRequest;

public interface ChangePasswordService {
    Protector<?> change(ChangePasswordRequest changePasswordRequest);
}
