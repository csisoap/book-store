package com.bookshopweb.manager;

import com.bookshopweb.dto.SignUpRequest;
import com.bookshopweb.utils.Protector;

public interface AuthService {
    Protector<?> signUp(SignUpRequest signUpRequest);
}
