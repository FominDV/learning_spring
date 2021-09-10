package ru.geekbrains.summer.market.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;
import ru.geekbrains.summer.market.TestUtil;
import ru.geekbrains.summer.market.dto.OrderDto;
import ru.geekbrains.summer.market.dto.OrderItemDto;
import ru.geekbrains.summer.market.model.User;
import ru.geekbrains.summer.market.services.OrderService;
import ru.geekbrains.summer.market.services.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @MockBean
    UserService userService;

    private final static String USERNAME = "Alex";
    private static User user;

    @BeforeAll
    public static void init() {
        user = new User();
        user.setId(1L);
        user.setEmail("user@mail.ru");
        user.setUsername(USERNAME);
        user.setPassword("******");
    }


    @Test
    @WithMockUser(username = USERNAME, authorities = "USER")
    public void createOrder_success() throws Exception {
        String address = "Volscay 1";
        String phone = "89998009922";
        Mockito.when(userService.findByUsername(USERNAME))
                .thenReturn(Optional.of(user));
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("address", address)
                        .param("phone", phone)
        ).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(orderService).createOrder(user, address, phone);
    }

    @ParameterizedTest
    @CsvSource({
            "'','89998009922'",
            "'Vocals 1',''",
            "'',''"
    })
    @WithMockUser(username = USERNAME, authorities = "USER")
    public void createOrder_badRequest(String address, String phone) throws Exception {
        String expectedEmptyAddressError = "Field 'address' cannot be null";
        String expectedEmptyPhoneError = "Field 'phone' cannot be null";
        Mockito.when(userService.findByUsername(USERNAME))
                .thenReturn(Optional.of(user));
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("address", address)
                                .param("phone", phone)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        if (!StringUtils.hasText(address)) {
            resultActions.andExpect(MockMvcResultMatchers.jsonPath("messages.[0]", is(expectedEmptyAddressError)));
            if (!StringUtils.hasText(phone)) {
                resultActions.andExpect(MockMvcResultMatchers.jsonPath("messages.[1]", is(expectedEmptyPhoneError)));
            }
        } else {
            resultActions.andExpect(MockMvcResultMatchers.jsonPath("messages.[0]", is(expectedEmptyPhoneError)));
        }
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "USER")
    public void getAllOrders() throws Exception {
        String address1 = "London";
        String address2 = "Volska 1, street Goovov";
        String phone1 = "81987773344";
        String phone2 = "81987876655";
        double priceOrder1 = 201.0;
        double priceOrder2 = 237.0;
        double priceProduct1 = 33.50;
        double priceProduct2 = 18;
        String title1 = "Milk";
        String title2 = "Bread";
        int quantity1 = 6;
        int quantity2 = 2;
        OrderItemDto orderItemDto1 = TestUtil.getOrderItemDto(1L, title1, priceProduct1, quantity1);
        OrderItemDto orderItemDto2 = TestUtil.getOrderItemDto(2L, title2, priceProduct2, quantity2);
        List<OrderDto> orderDtoList = List.of(
                getOrderDto(1L, address1, phone1, priceOrder1, List.of(orderItemDto1)),
                getOrderDto(2L, address2, phone2, priceOrder2, List.of(orderItemDto1, orderItemDto2))
        );
        Mockito.when(orderService.findAllDtosByUsername(USERNAME))
                .thenReturn(orderDtoList);
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/orders")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].address", is(address1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", is(priceOrder1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].items").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].items", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].address", is(address2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].price", is(priceOrder2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].items").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].items", hasSize(2)));
        assertOrderItem(resultActions, 0, 0, 1, title1, priceProduct1, quantity1);
        assertOrderItem(resultActions, 1, 0, 1, title1, priceProduct1, quantity1);
        assertOrderItem(resultActions, 1, 1, 2, title2, priceProduct2, quantity2);
    }

    private OrderDto getOrderDto(Long id, String address, String phone, double price, List<OrderItemDto> items) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setAddress(address);
        orderDto.setPhone(phone);
        orderDto.setPrice(BigDecimal.valueOf(price));
        orderDto.setItems(items);
        return orderDto;
    }

    private void assertOrderItem(ResultActions resultActions, int indexOrderArr, int indexItemsArr, int id, String title, double price, int quantity) throws Exception {
        String jsonPathPrefix = String.format("$.[%d].items.[%d]", indexOrderArr, indexItemsArr);
        resultActions.andExpect(MockMvcResultMatchers.jsonPath(jsonPathPrefix + ".productId", is(id)))
                .andExpect(MockMvcResultMatchers.jsonPath(jsonPathPrefix + ".productTitle", is(title)))
                .andExpect(MockMvcResultMatchers.jsonPath(jsonPathPrefix + ".pricePerProduct", is(price)))
                .andExpect(MockMvcResultMatchers.jsonPath(jsonPathPrefix + ".quantity", is(quantity)))
                .andExpect(MockMvcResultMatchers.jsonPath(jsonPathPrefix + ".price", is(quantity * price)));
    }

}