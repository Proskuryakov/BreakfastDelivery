package ru.relex.delivery.services.model.user;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface ExistingUser extends BaseUser {

  long getId();

  String getUsername();

  UserStatus getStatus();

  List<String> getListOfOrders();

}
