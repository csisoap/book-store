package com.bookshopweb.servlet.client;

import com.bookshopweb.beans.User;
import com.bookshopweb.consts.Enums;
import com.bookshopweb.dto.ChangePasswordRequest;
import com.bookshopweb.instance.ServiceFactory;
import com.bookshopweb.manager.ChangePasswordService;
import com.bookshopweb.manager.impl.ChangePasswordServiceImpl;
import com.bookshopweb.manager.impl.ChangePasswordServiceProxyImpl;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.HashingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ChangePassword", value = "/changePassword")
public class ChangePasswordServlet extends HomeServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/changePasswordView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(
                request.getParameter("currentPassword"),
                request.getParameter("newPassword"),
                request.getParameter("newPasswordAgain")
        );
        ChangePasswordService changePasswordService = new ChangePasswordServiceProxyImpl(request);
        changePasswordService.change(changePasswordRequest)
                .done((e) -> {
                    String successMessage = "Đổi mật khẩu thành công!";
                    request.setAttribute("successMessage", successMessage);
                })
                .fail((e) -> {
                    String errorMessage = "Đổi mật khẩu thất bại!";
                    request.setAttribute("errorMessage", errorMessage);
                });

        request.getRequestDispatcher("/WEB-INF/views/changePasswordView.jsp").forward(request, response);
    }
}
