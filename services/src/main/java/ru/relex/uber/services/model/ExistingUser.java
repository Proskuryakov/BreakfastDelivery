package ru.relex.uber.services.model;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface ExistingUser extends BaseUser {

  long getId();

  String getUsername();

  UserStatus getStatus();

  List<String> getListOfOrders();

}
