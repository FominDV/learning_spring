package ru.fomin.free_progect.beans;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.fomin.free_progect.entities.OrderItemEn;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
@Data
public class Cart {

   List<OrderItemEn> orderItemList;

    @PostConstruct
    public void init() {
        orderItemList = new ArrayList<>();
    }

}
