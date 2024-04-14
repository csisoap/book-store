package com.bookshopweb.servlet.client;

import com.bookshopweb.beans.Cart;
import com.bookshopweb.beans.CartItem;
import com.bookshopweb.beans.User;
import com.bookshopweb.consts.Enums;
import com.bookshopweb.dto.CartItemRequest;
import com.bookshopweb.dto.CartItemResponse;
import com.bookshopweb.dto.CartResponse;
import com.bookshopweb.dto.ErrorMessage;
import com.bookshopweb.dto.SuccessMessage;
import com.bookshopweb.instance.ServiceFactory;
import com.bookshopweb.manager.CartManager;
import com.bookshopweb.manager.impl.CartManagerImpl;
import com.bookshopweb.service.CartItemService;
import com.bookshopweb.service.CartService;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.JsonUtils;
import com.bookshopweb.utils.Protector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(name = "CartItemServlet", value = "/cartItem")
public class CartItemServlet extends HttpServlet {
    private final CartManager cartManager = new CartManagerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy userId và đối tượng user từ database theo userId này
        long userId = Protector.of(() -> Long.parseLong(request.getParameter("userId"))).get(0L);
        cartManager.getCartItemByUserId(userId)
                .done((cartItems) -> {
                    long cartId = cartItems.isEmpty() ? 0 : cartItems.stream().findFirst().orElse(null).getCartId();

                    List<CartItemResponse> cartItemResponses = cartItems.stream().map(cartItem -> new CartItemResponse()
                                    .setId(cartItem.getId())
                                    .setCartId(cartItem.getCartId())
                                    .setProductId(cartItem.getProductId())
                                    .setProductName(cartItem.getProduct().getName())
                                    .setProductPrice(cartItem.getProduct().getPrice())
                                    .setProductDiscount(cartItem.getProduct().getDiscount())
                                    .setProductQuantity(cartItem.getProduct().getQuantity())
                                    .setProductImageName(cartItem.getProduct().getImageName())
                                    .setQuantity(cartItem.getQuantity())).
                            collect(Collectors.toList());

                    CartResponse cartResponse = new CartResponse(cartId, userId, cartItemResponses);
                    JsonUtils.out(response, cartResponse, HttpServletResponse.SC_OK);
                })
                .fail((e) -> {
                    String errorMessage = "Đã có lỗi truy vấn!";
                    JsonUtils.out(response, new ErrorMessage(404, errorMessage), HttpServletResponse.SC_NOT_FOUND);
                });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy đối tượng cartItemRequest từ JSON trong request
        CartItemRequest cartItemRequest = JsonUtils.get(request, CartItemRequest.class);

        String successMessage = "Đã thêm sản phẩm vào giỏ hàng thành công!";
        String errorMessage = "Đã có lỗi truy vấn!";

        Runnable doneFunction = () -> JsonUtils.out(
                response,
                new SuccessMessage(200, successMessage),
                HttpServletResponse.SC_OK);
        Runnable failFunction = () -> JsonUtils.out(
                response,
                new ErrorMessage(404, errorMessage),
                HttpServletResponse.SC_NOT_FOUND);
        cartManager.createItem(cartItemRequest)
                .done(r -> doneFunction.run())
                .fail(e -> failFunction.run());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartItemRequest cartItemRequest = JsonUtils.get(request, CartItemRequest.class);

        long cartItemId = Protector.of(() -> Long.parseLong(request.getParameter("cartItemId"))).get(0L);

        String successMessage = "Đã cập nhật số lượng của sản phẩm thành công!";
        String errorMessage = "Đã có lỗi truy vấn!";

        Runnable doneFunction = () -> JsonUtils.out(
                response,
                new SuccessMessage(200, successMessage),
                HttpServletResponse.SC_OK);
        Runnable failFunction = () -> JsonUtils.out(
                response,
                new ErrorMessage(404, errorMessage),
                HttpServletResponse.SC_NOT_FOUND);

        if (cartItemId > 0L) {
            cartManager.updateItem(cartItemId, cartItemRequest)
                    .done(r -> doneFunction.run())
                    .fail(e -> failFunction.run());
        } else {
            failFunction.run();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long cartItemId = Protector.of(() -> Long.parseLong(request.getParameter("cartItemId"))).get(0L);

        String successMessage = "Đã xóa sản phẩm khỏi giỏ hàng thành công!";
        String errorMessage = "Đã có lỗi truy vấn!";

        Runnable doneFunction = () -> JsonUtils.out(
                response,
                new SuccessMessage(200, successMessage),
                HttpServletResponse.SC_OK);
        Runnable failFunction = () -> JsonUtils.out(
                response,
                new ErrorMessage(404, errorMessage),
                HttpServletResponse.SC_NOT_FOUND);

        if (cartItemId > 0L) {
            cartManager.deleteItem(cartItemId)
                    .done(r -> doneFunction.run())
                    .fail(e -> failFunction.run());
        } else {
            failFunction.run();
        }
    }
}
