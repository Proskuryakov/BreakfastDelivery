package ru.relex.delivery.services.validation;

import java.util.Map;

public class ValidationErrorsDishesFromBasket {
    private ValidationErrorsDishesFromBasket() {
    }

     public static final String USER_ID_MUST_BE_SET = "USER_ID_MUST_BE_SET";
    public static final String DISH_ID_MUST_BE_SET = "DISH_ID_MUST_BE_SET";
    public static final String COUNT_MUST_BE_SET = "COUNT_MUST_BE_SET";
    private static final Map<String, String> ERRORS = Map.ofEntries(
            Map.entry(USER_ID_MUST_BE_SET, "User id must be set"),
            Map.entry(DISH_ID_MUST_BE_SET, "Dish id must  be set"),
            Map.entry(COUNT_MUST_BE_SET, "Count   must be set")

    );
    public static String getMessageByCode(String code) {
        return ERRORS.get(code);
    }

}
