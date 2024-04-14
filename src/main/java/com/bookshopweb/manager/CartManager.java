package com.bookshopweb.manager;

import com.bookshopweb.beans.CartItem;
import com.bookshopweb.dto.CartItemRequest;
import com.bookshopweb.utils.Protector;

import java.util.List;

public interface CartManager {
    Protector<?> createItem(CartItemRequest cartItemRequest);

    Protector<?> updateItem(Long cartItemId, CartItemRequest cartItemRequest);

    Protector<?> deleteItem(Long cartItemId);

    Protector<List<CartItem>> getCartItemByUserId(Long userId);
}
