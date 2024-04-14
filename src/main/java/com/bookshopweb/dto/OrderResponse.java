package com.bookshopweb.dto;

import java.util.StringJoiner;

// builder parttern
public class OrderResponse {
    private long id;
    private String createdAt;
    private String name;
    private int status;
    private double total;

    public OrderResponse() {
    }
    public OrderResponse(long id, String createdAt, String name, int status, double total) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.status = status;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public OrderResponse setId(long id) {
        this.id = id;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public OrderResponse setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderResponse setName(String name) {
        this.name = name;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public OrderResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public OrderResponse setTotal(double total) {
        this.total = total;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderResponse.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("createdAt='" + createdAt + "'")
                .add("name='" + name + "'")
                .add("status=" + status)
                .add("total=" + total)
                .toString();
    }
}
