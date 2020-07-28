CREATE TABLE restaurant_types
(
    restaurant_type_id INTEGER PRIMARY KEY,
    name               VARCHAR(30) NOT NULL
);

CREATE TABLE restaurants
(
    restaurant_id   SERIAL PRIMARY KEY,
    restaurant_name VARCHAR(100),
    street          VARCHAR(50),
    building        VARCHAR(5),
    start_work_day  TIME,
    end_work_day    TIME
);

CREATE TABLE restaurant_restaurant_types
(
    restaurant_id      INTEGER,
    restaurant_type_id INTEGER,
    PRIMARY KEY (restaurant_id, restaurant_type_id),
    CONSTRAINT restaurant_restaurant_types_restaurant_id_fk
        FOREIGN KEY (restaurant_id) REFERENCES restaurants (restaurant_id) ON DELETE CASCADE,
    CONSTRAINT restaurant_restaurant_types_restaurant_type_id_fk
        FOREIGN KEY (restaurant_type_id) REFERENCES restaurant_types (restaurant_type_id) ON DELETE CASCADE
);
