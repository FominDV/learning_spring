insert into orders
    (price, user_id, address, phone)
values (246, 1, 'address', '89999803926');

insert into order_items
    (price, price_per_product, product_id, order_id, quantity)
values (190, 95, 1, 1, 2),
       (56, 28, 2, 1, 2);