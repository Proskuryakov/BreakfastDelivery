package ru.relex.uber.services.model;

import org.immutables.value.Value;

@Value.Immutable
public interface ExistingUser extends BaseUser {

  long getId();

  String getUsername();

  UserStatus getStatus();

}
