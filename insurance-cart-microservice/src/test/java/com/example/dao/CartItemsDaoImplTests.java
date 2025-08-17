package com.example.dao;

import com.example.entity.CartItems;
import com.example.repo.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartItemsDaoImplTests {
    @InjectMocks
    private CartItemsDao cartItemsDao = new CartItemsDaoImpl();
    @Mock
    private CartRepository cartRepository;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveItemToCartTest() {
        CartItems cartItem = CartItems.builder()
                .userId(1L)
                .policyId(101L)
                .build();

        cartItemsDao.saveItemToCart(cartItem);

        verify(cartRepository, times(1)).save(cartItem);
    }

    @Test
    public void deleteItemFromCartTest() {
        Long userId = 1L;
        Long policyId = 101L;

        cartItemsDao.deleteItemFromCart(userId, policyId);

        verify(cartRepository, times(1)).deleteByPolicyId(userId, policyId);
    }

    @Test
    public void getAllItemsForUserTest() {
        Long userId = 1L;
        List<CartItems> cartItemsList = new ArrayList<>();
        cartItemsList.add(CartItems.builder().userId(userId).policyId(101L).build());
        cartItemsList.add(CartItems.builder().userId(userId).policyId(102L).build());

        when(cartRepository.getListOfItemsForUserId(userId)).thenReturn(cartItemsList);

        List<CartItems> result = cartItemsDao.getAllItemsForUser(userId);

        assertNotNull(result);
        assertEquals(cartItemsList, result);
    }

    @Test
    public void removeListOfItemsTest() {
        List<CartItems> cartItemsList = new ArrayList<>();
        cartItemsList.add(CartItems.builder().userId(1L).policyId(101L).build());
        cartItemsList.add(CartItems.builder().userId(2L).policyId(102L).build());
        cartItemsDao.removeListOfItems(cartItemsList);
        verify(cartRepository, times(1)).deleteAll(cartItemsList);
    }

    @Test
    public void clearCartForUserTest() {
        Long userId = 1L;
        cartItemsDao.clearCartForUser(userId);
        verify(cartRepository, times(1)).deleteAllByUserId(userId);
    }
}
