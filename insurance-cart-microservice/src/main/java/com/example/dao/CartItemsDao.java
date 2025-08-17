package com.example.dao;

import com.example.entity.CartItems;

import java.util.List;

public interface CartItemsDao {
    public abstract void saveItemToCart(final CartItems cartItem);

    public abstract void deleteItemFromCart(final Long userId, final Long policyId);

    public abstract List<CartItems> getAllItemsForUser(final Long userId);

    public abstract void removeListOfItems(final List<CartItems> userId);

    public abstract void clearCartForUser(final Long userId);
}
