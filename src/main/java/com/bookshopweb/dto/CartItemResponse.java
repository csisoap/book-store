package com.bookshopweb.dto;

import java.util.StringJoiner;


// builder parttern
public class CartItemResponse {
    private long id;
    private long cartId;
    private long productId;
    private String productName;
    private double productPrice;
    private double productDiscount;
    private int productQuantity;
    private String productImageName;
    private int quantity;

    public CartItemResponse() {
    }

    public CartItemResponse(long id,
                            long cartId,
                            long productId,
                            String productName,
                            double productPrice,
                            double productDiscount,
                            int productQuantity,
                            String productImageName,
                            int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productQuantity = productQuantity;
        this.productImageName = productImageName;
        this.quantity = quantity;
    }

    public CartItemResponse setId(long id) {
        this.id = id;
        return this;
    }

    public CartItemResponse setCartId(long cartId) {
        this.cartId = cartId;
        return this;
    }

    public CartItemResponse setProductId(long productId) {
        this.productId = productId;
        return this;
    }

    public CartItemResponse setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public CartItemResponse setProductPrice(double productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public CartItemResponse setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
        return this;
    }

    public CartItemResponse setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
        return this;
    }

    public CartItemResponse setProductImageName(String imgName) {
        this.productImageName = imgName;
        return this;
    }

    public CartItemResponse setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public long getId() {
        return id;
    }

    public long getCartId() {
        return cartId;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getProductDiscount() {
        return productDiscount;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getProductImageName() {
        return productImageName;
    }

    public int getQuantity() {
        return quantity;
    }



    @Override
    public String toString() {
        return new StringJoiner(", ", CartItemResponse.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("cartId=" + cartId)
                .add("productId=" + productId)
                .add("productName='" + productName + "'")
                .add("productPrice=" + productPrice)
                .add("productDiscount=" + productDiscount)
                .add("productQuantity=" + productQuantity)
                .add("productImageName='" + productImageName + "'")
                .add("quantity=" + quantity)
                .toString();
    }
}
