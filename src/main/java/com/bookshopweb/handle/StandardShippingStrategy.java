package com.bookshopweb.handle;


// Concrete ShippingStrategy implementations
public class StandardShippingStrategy implements ShippingStrategy{
    @Override
    public long calculateShippingCost() {
        return 15000l;
    }

}