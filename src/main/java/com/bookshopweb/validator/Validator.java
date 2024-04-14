package com.bookshopweb.validator;

import com.bookshopweb.utils.Protector;

public interface Validator<T> {
    void validate(T data) throws Exception;
}
