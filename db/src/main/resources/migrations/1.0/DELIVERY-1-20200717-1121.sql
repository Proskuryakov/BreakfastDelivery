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
    dish_cooking_time_minutes INTEGER
);

CREATE TABLE dish_dish_types
(
    dish_id INTEGER,
    dish_type_id INTEGER,
    PRIMARY KEY (dish_id, dish_type_id),
    CONSTRAINT dish_dish_types_dish_id_fk FOREIGN KEY (dish_id) REFERENCES dishes (dish_id) ON DELETE CASCADE,
    CONSTRAINT dish_dish_types_dish_type_id_fk FOREIGN KEY (dish_type_id) REFERENCES dish_types (dish_type_id) ON DELETE CASCADE
);

