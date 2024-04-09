package com.bookshopweb.instance;

import com.bookshopweb.consts.Enums;
import com.bookshopweb.service.CartItemService;
import com.bookshopweb.service.CartService;
import com.bookshopweb.service.CategoryService;
import com.bookshopweb.service.OrderItemService;
import com.bookshopweb.service.OrderService;
import com.bookshopweb.service.ProductReviewService;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.service.Service;
import com.bookshopweb.service.UserService;
import com.bookshopweb.service.WishlistItemService;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static final Service getService(Enums.ServiceType serviceType) {
        switch (serviceType) {
            case CART:
                return CartService.getInstance();
            case CART_ITEM:
                return CartItemService.getInstance();
            case CATEGORY:
                return CategoryService.getInstance();
            case ORDER_ITEM:
                return OrderItemService.getInstance();
            case ORDER:
                return OrderService.getInstance();
            case PRODUCT:
                return ProductService.getInstance();
            case PRODUCT_REVIEW:
                return ProductReviewService.getInstance();
            case USER:
                return UserService.getInstance();
            case WISHLIST_ITEM:
                return WishlistItemService.getInstance();
            default:
                throw new IllegalArgumentException("This service type is unsupported");
        }
    }
}

