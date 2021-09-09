package ru.geekbrains.summer.market.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.summer.market.dto.OrderItemDto;
import ru.geekbrains.summer.market.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Cart.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CartTest {

    @Autowired
    Cart cart;

    int quantityItem1 = 3;
    int quantityItem2 = 5;
    List<OrderItemDto> orderItemDtoList;
    BigDecimal price;

    @BeforeEach
    public void prepare() {
        orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(getOrderItemDto(1L, "Milk", 34.5, quantityItem1));
        orderItemDtoList.add(getOrderItemDto(2L, "Potato", 65.99, quantityItem2));
        price = calculateTotalPrice();
        cart.setItems(new ArrayList<>(orderItemDtoList));
        cart.setPrice(price);
    }

    @Test
    public void clear() {
        cart.clear();
        assertEquals(Collections.emptyList(), cart.getItems());
        assertEquals(BigDecimal.ZERO, cart.getPrice());
    }

    @ParameterizedTest
    @CsvSource({
            "1, true",
            "3, false"
    })
    public void add_byProductId(Long productId, boolean expectedResult) {
        boolean actualResult = cart.add(productId);
        assertEquals(expectedResult, actualResult);
        if (expectedResult) {
            int expectedQuantity = getIncrementedQuantityById(productId);
            assertEquals(expectedQuantity, getQuantityById(productId));
            assertEquals(price.add(getPriceById(productId)), cart.getPrice());
        }
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForAdd_byProductTest")
    public void add_byProduct(Product product) {
        Long productId = product.getId();
        int expectedQuantity = itemsHasProductId(productId) ? getIncrementedQuantityById(productId) : 1;
        int expectedItemListSize = itemsHasProductId(productId) ? cart.getItems().size() : cart.getItems().size() + 1;
        cart.add(product);
        assertEquals(expectedQuantity, getQuantityById(productId));
        assertEquals(expectedItemListSize, cart.getItems().size());
        assertEquals(price.add(product.getPrice()), cart.getPrice());
    }

    @ParameterizedTest
    @ValueSource(longs = {1L,2L})
    public void remove_availableId(Long productId){
        cart.remove(productId);
        assertEquals(1, cart.getItems().size());
    }

    @Test
    public void remove_unavailableId(){
        cart.remove(3L);
        assertEquals(2, cart.getItems().size());
    }

    private OrderItemDto getOrderItemDto(Long productId, String productTitle, double productPrice, int quantity) {
        BigDecimal pricePerProduct = BigDecimal.valueOf(productPrice);
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(productId);
        orderItemDto.setProductTitle(productTitle);
        orderItemDto.setPricePerProduct(pricePerProduct);
        orderItemDto.setPrice(pricePerProduct.multiply(BigDecimal.valueOf(quantity)));
        orderItemDto.setQuantity(quantity);
        return orderItemDto;
    }

    private BigDecimal calculateTotalPrice() {
        return orderItemDtoList.stream()
                .map(OrderItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int getQuantityById(long id) {
        return itemsHasProductId(id) ?
                cart.getItems().get((int) (id - 1)).getQuantity() :
                0;
    }

    private BigDecimal getPriceById(long id) {
        return cart.getItems().get((int) (id - 1)).getPricePerProduct();
    }

    private int getIncrementedQuantityById(Long productId) {
        return getStartQuantity(productId) + 1;
    }

    private int getStartQuantity(Long productId) {
        return productId == 1L ? quantityItem1 : quantityItem2;
    }

    private boolean itemsHasProductId(Long productId) {
        return cart.getItems().size() >= productId;
    }

    private Product getProduct(OrderItemDto orderItemDto) {
        Product product = new Product();
        product.setId(orderItemDto.getProductId());
        product.setTitle(orderItemDto.getProductTitle());
        product.setPrice(orderItemDto.getPricePerProduct());
        return product;
    }

    private Product getProduct(Long id, String title, double price) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(BigDecimal.valueOf(price));
        return product;
    }

    private Stream<Product> getArgumentsForAdd_byProductTest() {
        return Stream.of(
                getProduct(orderItemDtoList.get(1)),
                getProduct(3L, "Pasta", 77.00)
        );
    }

}