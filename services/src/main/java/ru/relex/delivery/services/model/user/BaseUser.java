package ru.relex.delivery.services.model.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import ru.relex.delivery.services.validation.ValidationErrorsUser;

public interface BaseUser {

  @NotNull(message = ValidationErrorsUser.ROLE_MUST_BE_SET)
  Role getRole();

  @Valid
  @NotNull(message = ValidationErrorsUser.PERSONAL_INFO_MUST_BE_SET)
  PersonalInfo getPersonalInfo();

}
