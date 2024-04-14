package com.bookshopweb.servlet.client;

import com.bookshopweb.beans.User;
import com.bookshopweb.consts.Enums;
import com.bookshopweb.dto.SignUpRequest;
import com.bookshopweb.instance.ServiceFactory;
import com.bookshopweb.manager.AuthService;
import com.bookshopweb.manager.impl.AuthServiceImpl;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.HashingUtils;
import com.bookshopweb.utils.Protector;
import com.bookshopweb.utils.Validator;
import com.bookshopweb.validator.impl.SignUpValidatorAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "SignupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {

    private final AuthService authService = new AuthServiceImpl(
            new SignUpValidatorAdapter()
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/signupView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lưu các parameter (tên-giá trị) vào map values
        Map<String, String> values = new HashMap<>();
        values.put("username", request.getParameter("username"));
        values.put("password", request.getParameter("password"));
        values.put("fullname", request.getParameter("fullname"));
        values.put("email", request.getParameter("email"));
        values.put("phoneNumber", request.getParameter("phoneNumber"));
        values.put("gender", request.getParameter("gender"));
        values.put("address", request.getParameter("address"));
        values.put("policy", request.getParameter("policy"));


        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .fullname(request.getParameter("fullname"))
                .email(request.getParameter("email"))
                .phoneNumber(request.getParameter("phoneNumber"))
                .gender(request.getParameter("gender"))
                .address(request.getParameter("address"))
                .policy(request.getParameter("policy"))
                .build();

        String successMessage = "Đã đăng ký thành công!";
        String errorMessage = "Đã có lỗi truy vấn!";

        authService.signUp(signUpRequest)
                .done((r) -> {
                    request.setAttribute("successMessage", successMessage);
                })
                .fail((e) -> {
                    request.setAttribute("values", values);
                    request.setAttribute("errorMessage", errorMessage);
                });

        request.getRequestDispatcher("/WEB-INF/views/signupView.jsp").forward(request, response);
    }
}
