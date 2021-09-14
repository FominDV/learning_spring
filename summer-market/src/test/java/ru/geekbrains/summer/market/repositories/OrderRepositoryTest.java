package ru.geekbrains.summer.market.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.summer.market.model.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("Test")
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void save() {

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

}