package ru.relex.delivery.services.validation;

import java.util.Map;

public class ValidationErrorsRestaurant {

  public static final String RESTAURANT_NAME_MUST_BE_SET = "RESTAURANT_NAME_MUST_BE_SET";
  public static final String RESTAURANT_NAME_LENGTH_IS_INVALID = "RESTAURANT_NAME_LENGTH_IS_INVALID";
  public static final String RESTAURANT_TYPE_MUST_BE_SET = "RESTAURANT_TYPE_MUST_BE_SET";
  public static final String RESTAURANT_ADDRESS_MUST_BE_SET = "RESTAURANT_ADDRESS_MUST_BE_SET";
  public static final String RESTAURANT_WORKING_HOURS_MUST_BE_SET = "RESTAURANT_WORKING_HOURS_MUST_BE_SET";
  public static final String RESTAURANT_STREET_IN_ADDRESS_MUST_BE_SET = "RESTAURANT_STREET_IN_ADDRESS_MUST_BE_SET";
  public static final String RESTAURANT_BUILDING_IN_ADDRESS_MUST_BE_SET = "RESTAURANT_BUILDING_IN_ADDRESS_MUST_BE_SET";
  public static final String RESTAURANT_STREET_LENGTH_IS_INVALID = "RESTAURANT_STREET_LENGTH_IS_INVALID";
  public static final String RESTAURANT_BUILDING_LENGTH_IS_INVALID = "RESTAURANT_BUILDING_LENGTH_IS_INVALID";
  public static final String RESTAURANT_START_WORK_DAY_IN_ADDRESS_MUST_BE_SET = "RESTAURANT_START_WORK_DAY_IN_ADDRESS_MUST_BE_SET";
  public static final String RESTAURANT_END_WORK_DAY_IN_ADDRESS_MUST_BE_SET = "RESTAURANT_END_WORK_DAY_IN_ADDRESS_MUST_BE_SET";
  public static final String RESTAURANT_IMAGE_MUST_BE_SET = "RESTAURANT_IMAGE_MUST_BE_SET";


    public ValidationErrorsRestaurant() {
  }

  private static final Map<String, String> ERRORS = Map.ofEntries(
    Map.entry(RESTAURANT_NAME_MUST_BE_SET, "Name must be set"),
    Map.entry(RESTAURANT_TYPE_MUST_BE_SET, "Type must be set"),
    Map.entry(RESTAURANT_ADDRESS_MUST_BE_SET, "Address must be set"),
    Map.entry(RESTAURANT_WORKING_HOURS_MUST_BE_SET, "Working hours must be set"),
    Map.entry(RESTAURANT_STREET_IN_ADDRESS_MUST_BE_SET, "Restaurant street in address must be set"),
    Map.entry(RESTAURANT_BUILDING_IN_ADDRESS_MUST_BE_SET, "Restaurant building in address must be set"),
    Map.entry(RESTAURANT_IMAGE_MUST_BE_SET, "Restaurant image must be set")
  );

  public static String getMessageByCode(String code) {
    return ERRORS.get(code);
  }

}
