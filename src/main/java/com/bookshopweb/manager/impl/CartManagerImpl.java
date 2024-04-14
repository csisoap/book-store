package com.bookshopweb.manager.impl;

import com.bookshopweb.beans.Cart;
import com.bookshopweb.beans.CartItem;
import com.bookshopweb.beans.Product;
import com.bookshopweb.beans.User;
import com.bookshopweb.consts.Enums;
import com.bookshopweb.dto.CartItemRequest;
import com.bookshopweb.instance.ServiceFactory;
import com.bookshopweb.manager.CartManager;
import com.bookshopweb.service.CartItemService;
import com.bookshopweb.service.CartService;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.Protector;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// facade pattern
public class CartManagerImpl implements CartManager {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    public CartManagerImpl() {
        cartService = (CartService) ServiceFactory.getService(Enums.ServiceType.CART);
        cartItemService = (CartItemService) ServiceFactory.getService(Enums.ServiceType.CART_ITEM);
        userService = (UserService) ServiceFactory.getService(Enums.ServiceType.USER);
        productService = (ProductService) ServiceFactory.getService(Enums.ServiceType.PRODUCT);
    }


    @Override
    public Protector<?> createItem(CartItemRequest cartItemRequest) {
        Optional<Cart> cartFromServer = Protector.of(() -> cartService.getByUserId(cartItemRequest.getUserId()))
                .get(Optional::empty);
        // Nhận cartId từ cartFromServer (nếu đã có) hoặc cart mới (nếu chưa có)
        long cartId;

        if (cartFromServer.isPresent()) {
            cartId = cartFromServer.get().getId();
        } else {
            Cart cart = new Cart(0L, cartItemRequest.getUserId(), LocalDateTime.now(), null);
            cartId = Protector.of(() -> cartService.insert(cart)).get(0L);
        }
        Optional<Product> productOpt = productService.getById(cartItemRequest.getProductId());
        if(!productOpt.isPresent()) {
            return Protector.error(new Exception("Not found product with productId=" + cartItemRequest.getProductId()));
        }

        Product product = productOpt.get();
        if(product.getQuantity() < cartItemRequest.getQuantity()) {
            return Protector.error(new Exception("Not enough product quantity"));
        }

        product.setQuantity(product.getQuantity() - cartItemRequest.getQuantity());
        Protector<?> protector = Protector.of(() -> productService.update(product));

        // Nếu cart của user này đã có trong database (cardId lớn hơn O)
        if (cartId > 0L) {
            // Lấy đối tượng cartItem từ database theo cartId và productId của cartItemRequest
            Optional<CartItem> cartItemFromServer = Protector.of(() -> cartItemService.getByCartIdAndProductId(
                    cartId, cartItemRequest.getProductId()
            )).get(Optional::empty);

            // Nếu cartItem của cartId và productId này đã có trong database
            if (cartItemFromServer.isPresent()) {
                CartItem cartItem = cartItemFromServer.get();
                cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());
                cartItem.setUpdatedAt(LocalDateTime.now());
                return Protector.of(() -> cartItemService.update(cartItem));
            } else {
                CartItem cartItem = new CartItem(
                        0L,
                        cartId,
                        cartItemRequest.getProductId(),
                        cartItemRequest.getQuantity(),
                        LocalDateTime.now(),
                        null
                );
                return Protector.of(() -> cartItemService.insert(cartItem));
            }
        } else {
            return Protector.error(
                    new Exception("Not found cart")
            );
        }
    }

    @Override
    public Protector<?> updateItem(Long cartItemId, CartItemRequest cartItemRequest) {
        Optional<CartItem> cartItemFromServer = Protector.of(() -> cartItemService.getById(cartItemId)).get(Optional::empty);

        if(!cartItemFromServer.isPresent()) {
            return Protector.error(new Exception("Not found cartItem for id=" + cartItemId));
        }
        CartItem cartItem = cartItemFromServer.get();
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setUpdatedAt(LocalDateTime.now());
        return Protector.of(() -> cartItemService.update(cartItem));
    }

    @Override
    public Protector<?> deleteItem(Long cartItemId) {
        return Protector.of(() -> cartItemService.delete(cartItemId));
    }

    @Override
    public Protector<List<CartItem>> getCartItemByUserId(Long userId) {
        Optional<User> userFromServer = Protector.of(() -> userService.getById(userId)).get(Optional::empty);

        // Nếu userId là số nguyên dương và có hiện diện trong bảng user
        if (userId > 0L && userFromServer.isPresent()) {
            // Lấy đối tượng cart từ database theo userId
            Optional<Cart> cartFromServer = Protector.of(() -> cartService.getByUserId(userId)).get(Optional::empty);

            // Nếu cart của user này đã có trong database
            if (cartFromServer.isPresent()) {
                long cartId = cartFromServer.get().getId();
                return Protector.of(() -> cartItemService.getByCartId(cartId));
            } else {
                return Protector.of(() -> new ArrayList<>());
            }
        } else {
            return Protector.error(new Exception("Not found Cart for user=" + userId));
        }
    }


}
