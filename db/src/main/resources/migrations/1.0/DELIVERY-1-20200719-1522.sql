
drop table orders ,dishesFromOrder cascade;
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
     status_id INTEGER DEFAULT '1',
       checkres INTEGER
 );

 CREATE TABLE  dishesFromOrder
(
     position_id    SERIAL PRIMARY KEY,
     order_id   INTEGER   ,
     count INTEGER NOT NULL DEFAULT '1',
     dish_id VARCHAR(50)  NOT NULL,
     FOREIGN KEY (order_id) REFERENCES orders(order_id)
 );




