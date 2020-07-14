package ru.relex.uber.services.internal;

import ru.relex.uber.services.model.ExistingUser;
import ru.relex.uber.services.model.NewUser;
import ru.relex.uber.services.model.UpdatableUser;

public interface UserService {

  ExistingUser createUser(NewUser user);

  ExistingUser getById(long id);

  ExistingUser update(long id, UpdatableUser updatableUser);

  boolean deleteById(long id);
}
