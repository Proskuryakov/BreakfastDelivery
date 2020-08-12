package ru.relex.delivery.commons.model;

public enum RestaurantType {

  BAR(1),
  PIZZERIA(2),
  BURGER(3),
  DUMPLINGS(4),
  SUSHI(5),
  BAKERY(6),
  RUSSIAN(7),
  ITALIAN(8),
  CHINESE(9),
  GEORGIAN(10),
  ARMENIAN(11),
  COMMON(12);

  private final int id;

  RestaurantType(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static RestaurantType fromId(Integer id) {
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
