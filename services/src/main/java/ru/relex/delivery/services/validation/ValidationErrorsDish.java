package ru.relex.delivery.services.validation;

import java.util.Map;

public final class ValidationErrorsDish {

  private ValidationErrorsDish() {
  }


  public static final String DISH_NAME_LENGTH_IS_INVALID = "DISH_NAME_LENGTH_IS_INVALID";
  //public static final String DISH_PRICE_IS_INVALID = "DISH_PRICE_IS_INVALID";
  public static final String MAIN_DISH_INFO_MUST_BE_SET = "MAIN_DISH_INFO_MUST_BE_SET";
  public static final String DISH_TYPE_MUST_BE_SET = "DISH_TYPE_MUST_BE_SET";
  public static final String DISH_CALORIES_MUST_BE_SET = "DISH_CALORIES_MUST_BE_SET";
  public static final String DISH_COOKING_TIME_MINUTES_MUST_BE_SET = "DISH_COOKING_TIME_MINUTES_MUST_BE_SET";
  public static final String DISH_NAME_IN_MAIN_DISH_INFO_MUST_BE_SET = "DISH_NAME_IN_MAIN_DISH_INFO_MUST_BE_SET";
  public static final String DISH_PRICE_IN_MAIN_DISH_INFO_MUST_BE_SET = "DISH_PRICE_IN_MAIN_DISH_INFO_MUST_BE_SET";

  private static final Map<String, String> ERRORS = Map.ofEntries(
    Map.entry(DISH_TYPE_MUST_BE_SET, "Dish type must be set"),
    Map.entry(MAIN_DISH_INFO_MUST_BE_SET, "Main dish info must be set"),
    Map.entry(DISH_CALORIES_MUST_BE_SET, "Dish calories must be set"),
    Map.entry(DISH_COOKING_TIME_MINUTES_MUST_BE_SET, "Dish cooking time (minutes) must be set"),
    Map.entry(DISH_NAME_IN_MAIN_DISH_INFO_MUST_BE_SET, "Dish name in main dish info  must be set"),
    Map.entry(DISH_PRICE_IN_MAIN_DISH_INFO_MUST_BE_SET, "Dish price in main dish info  must be set")
  );


  public static String getMessageByCode(String code) {
    return ERRORS.get(code);
  }
}
