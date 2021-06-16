package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fomin.free_progect.beans.Cart;
import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.services.CartService;
import ru.fomin.free_progect.services.ProductPriceService;

import javax.annotation.Resource;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartServiceImpl implements CartService {

    @Resource
    ProductPriceService productPriceService;

    Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addProduct(Long productId) {
        OrderItem addingOrderItem = productPriceService.getOrderItem(productId);
        cart.addProduct(addingOrderItem);
    }

}
