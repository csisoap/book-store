package com.bookshopweb.servlet.admin;

import com.bookshopweb.consts.Enums;
import com.bookshopweb.instance.ServiceFactory;
import com.bookshopweb.service.CategoryService;
import com.bookshopweb.service.OrderService;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.Protector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    private final UserService userService = (UserService) ServiceFactory.getService(Enums.ServiceType.USER);
    private final CategoryService categoryService = (CategoryService) ServiceFactory.getService(Enums.ServiceType.CATEGORY);
    private final ProductService productService = (ProductService) ServiceFactory.getService(Enums.ServiceType.PRODUCT);
    private final OrderService orderService = (OrderService) ServiceFactory.getService(Enums.ServiceType.ORDER);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalUsers = Protector.of(userService::count).get(0);
        int totalCategories = Protector.of(categoryService::count).get(0);
        int totalProducts = Protector.of(productService::count).get(0);
        int totalOrders = Protector.of(orderService::count).get(0);
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalCategories", totalCategories);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("totalOrders", totalOrders);
        request.getRequestDispatcher("/WEB-INF/views/adminView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
