package ru.relex.delivery.services.validation;

import java.util.Map;

public final class ValidationErrorsUser {
  public static final String EMAIL_HAS_INVALID_FORMAT = "EMAIL_HAS_INVALID_FORMAT";

  private ValidationErrorsUser() {
  }


  public static final String LAST_NAME_LENGTH_IS_INVALID = "LAST_NAME_LENGTH_IS_INVALID";
  public static final String FIRST_NAME_LENGTH_IS_INVALID = "FIRST_NAME_LENGTH_IS_INVALID";
  public static final String PERSONAL_INFO_MUST_BE_SET = "PERSONAL_INFO_MUST_BE_SET";
  public static final String ROLE_MUST_BE_SET = "ROLE_MUST_BE_SET";

  private static final Map<String, String> ERRORS = Map.ofEntries(
    Map.entry(ROLE_MUST_BE_SET, "UserRole must be set"),
    Map.entry(PERSONAL_INFO_MUST_BE_SET, "Personal info must be set")
  );


  public static String getMessageByCode(String code) {
    return ERRORS.get(code);
  }
}
