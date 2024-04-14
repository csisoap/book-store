package com.bookshopweb.handle;

public class ExpressShippingStrategy implements ShippingStrategy {
    @Override
    public long calculateShippingCost() {
        return 50000l;
    }
}