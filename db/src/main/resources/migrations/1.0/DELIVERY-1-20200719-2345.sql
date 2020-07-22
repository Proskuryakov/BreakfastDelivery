CREATE TABLE  statusesOFOrder
(
status_id INTEGER PRIMARY KEY,
name_status  VARCHAR(30) NOT NULL

);
CREATE TABLE  orders
(
    order_id    SERIAL PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    phone      VARCHAR(15)  NOT NULL UNIQUE,
    city   VARCHAR(100)  NOT NULL ,
    street   VARCHAR(100)  NOT NULL ,
    house   VARCHAR(10)  NOT NULL ,
    flat   VARCHAR(10)  NOT NULL ,
    entrance   VARCHAR(10)  NOT NULL,
    floor   VARCHAR(10)  NOT NULL ,
    status_id INTEGER NOT NULL,
    FOREIGN KEY (status_id) REFERENCES statusesOFOrder(status_id)
);

CREATE TABLE  dishesFromOrder
(
    position_id    SERIAL PRIMARY KEY,
     order_id   INTEGER   ,
     count INTEGER NOT NULL DEFAULT '1',
     dish_id VARCHAR(50)  NOT NULL,
     FOREIGN KEY (order_id) REFERENCES orders(order_id)
 );


INSERT INTO public.statusesOFOrder (status_id, name_status)
VALUES (1, 'ORDER_IN_PROCESSING'),
       (2, 'ORDER_PREPARING'),
       (3, 'ORDER_DELIVERY'),
       (4, 'ORDER_DELIVERED'),
       (5, 'ORDER_CANCELLED');
INSERT INTO public.orders (  phone, city, street, house, flat, entrance , floor  , status_id)
VALUES (  '8598478987', 'voronezh', 'begovaya', '56', '14', '5','2', '5');
INSERT INTO public.dishesFromOrder (   order_id,  dish_id )
VALUES ( '1','5' );
INSERT INTO public.dishesFromOrder (   order_id,  dish_id , count)
VALUES ( '1','5' , '6');
INSERT INTO public.dishesFromOrder (   order_id,  dish_id , count)
VALUES ( '1','2' , '6');
INSERT INTO public.dishesFromOrder (   order_id,  dish_id , count)
VALUES ( '5','2' , '6');
INSERT INTO public.dishesFromOrder (   order_id,  dish_id )
VALUES ( '1','3' );