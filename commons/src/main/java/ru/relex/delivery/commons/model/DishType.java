package ru.relex.delivery.commons.model;

public enum DishType {

  DRINK(1),
  MAIN(2),
  SALAD(3),
  SANDWICH(4),
  BURGER(5),
  DESSERT(6),
  PIZZA(7),
  SUSHI(8),
  BAKERY(9);

  private final int id;

  DishType(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static DishType fromId(Integer id) {
    if (id == null) {
      return null;
    }

    for (var value: values()) {
      if (id.equals(value.id)) {
        return value;
      }
    }

    return null;
  }
}
