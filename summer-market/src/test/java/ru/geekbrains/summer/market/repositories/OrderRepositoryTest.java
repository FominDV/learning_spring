package ru.geekbrains.summer.market.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.summer.market.model.Order;
import ru.geekbrains.summer.market.model.OrderItem;
import ru.geekbrains.summer.market.model.Product;
import ru.geekbrains.summer.market.model.User;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("Test")
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void save() {
        String address = "Address exp";
        String phone = "89098880921";
        Order order = new Order();
        order.setAddress(address);
        order.setPhone(phone);
        List<OrderItem> orderItemList = List.of(
                getOrderItem(entityManager.find(Product.class, 6L), 5, order),
                getOrderItem(entityManager.find(Product.class, 3L), 2, order)
        );
        BigDecimal orderPrice = orderItemList.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setItems(orderItemList);
        order.setUser(entityManager.find(User.class, 2L));
        order.setPrice(orderPrice);

        orderRepository.save(order);

        assertEquals(2, orderRepository.findAll().size());
    }

    @ParameterizedTest
    @CsvSource({
            "user, 1",
            "admin, 0"
    })
    public void findAllByUsername(String username, int expectedSize) {
        List<Order> orderList = orderRepository.findAllByUsername(username);
        assertNotNull(orderList);
        assertEquals(expectedSize, orderList.size());
    }

    private OrderItem getOrderItem(Product product, int quantity, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPricePerProduct(product.getPrice());
        orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        orderItem.setOrder(order);
        return orderItem;
    }

}