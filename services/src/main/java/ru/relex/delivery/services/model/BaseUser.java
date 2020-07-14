package ru.relex.delivery.services.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import ru.relex.delivery.services.validation.ValidationErrors;

public interface BaseUser {

  @NotNull(message = ValidationErrors.ROLE_MUST_BE_SET)
  Role getRole();

  @Valid
  @NotNull(message = ValidationErrors.PERSONAL_INFO_MUST_BE_SET)
  PersonalInfo getPersonalInfo();

}
