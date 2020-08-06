drop table dish_types, dishes, dish_dish_types, orders, dishesFromOrder, dishesFromBasket;
CREATE TABLE dish_types
(
    dish_type_id INTEGER PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE dishes
(
    dish_id SERIAL PRIMARY KEY,
    dish_name VARCHAR(50),
    dish_price INTEGER,
    dish_calories INTEGER,
    dish_cooking_time_minutes INTEGER,
    dish_image VARCHAR(2000),
    restaurant_id INTEGER
);

CREATE TABLE dish_dish_types
(
    dish_id INTEGER,
    dish_type_id INTEGER,
    PRIMARY KEY (dish_id, dish_type_id),
    CONSTRAINT dish_dish_types_dish_id_fk FOREIGN KEY (dish_id) REFERENCES dishes (dish_id) ON DELETE CASCADE,
    CONSTRAINT dish_dish_types_dish_type_id_fk FOREIGN KEY (dish_type_id) REFERENCES dish_types (dish_type_id) ON DELETE CASCADE
);

INSERT INTO dish_types(dish_type_id, name)
VALUES (1, 'DRINK'),
       (2, 'MAIN'),
       (3, 'SALAD'),
       (4, 'SANDWICH'),
       (5, 'BURGER'),
       (6, 'DESSERT'),
       (7, 'PIZZA'),
       (8, 'SUSHI'),
       (9, 'BAKERY');

 CREATE TABLE  orders
(
user_id INTEGER UNIQUE,
    order_id    SERIAL PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    phone      VARCHAR(15)  NOT NULL UNIQUE,
    city   VARCHAR(100)  NOT NULL ,
    street   VARCHAR(100)  NOT NULL ,
    house   VARCHAR(10)  NOT NULL ,
    flat   VARCHAR(10)   ,
    entrance   VARCHAR(10)   ,
    floor   VARCHAR(10)    ,
     status_id INTEGER DEFAULT '1',
       checkres INTEGER
 );

 CREATE TABLE  dishesFromOrder
(
     position_id    SERIAL PRIMARY KEY,
     order_id   INTEGER   ,
     count INTEGER NOT NULL DEFAULT '1',
     dish_id VARCHAR(50)  NOT NULL   ,
     FOREIGN KEY (order_id) REFERENCES orders(order_id)
 );
CREATE TABLE dishesFromBasket
(
    res_id SERIAL PRIMARY KEY,
    user_id INTEGER,
    dish_id INTEGER  ,
    count INTEGER,
    UNIQUE   (user_id, dish_id)

);