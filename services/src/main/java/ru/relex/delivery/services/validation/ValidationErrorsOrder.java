package ru.relex.delivery.services.validation;

import java.util.Map;

public final class ValidationErrorsOrder {
    private ValidationErrorsOrder() {
    }

    public static final String LAST_NAME_LENGTH_IS_INVALID = "LAST_NAME_LENGTH_IS_INVALID";
    public static final String FIRST_NAME_LENGTH_IS_INVALID = "FIRST_NAME_LENGTH_IS_INVALID";
    public static final String PERSONAL_INFO_MUST_BE_SET = "PERSONAL_INFO_MUST_BE_SET";
    public static final String LIST_OF_DISHES_MUST_BE_SET = "LIST_OF_DISHES_MUST_BE_SET";
    private static final Map<String, String> ERRORS = Map.ofEntries(
            Map.entry(LIST_OF_DISHES_MUST_BE_SET, "list of dishes must be set "),
            Map.entry(PERSONAL_INFO_MUST_BE_SET, "Personal info must be set")
    );

    public static String getMessageByCode(String code) {
        return ERRORS.get(code);
    }

}
