package ru.relex.delivery.services.internal.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.commons.model.UserRole;
import ru.relex.delivery.db.mapper.UserMapper;
import ru.relex.delivery.db.model.UserModel;
import ru.relex.delivery.services.mapper.UserStruct;
import ru.relex.delivery.services.model.user.*;
import ru.relex.delivery.services.internal.UserService;
import ru.relex.delivery.services.security.PasswordEncryptor;

@Service
public class UserServiceImpl implements UserService {

  private final Map<Long, ExistingUser> USERS = new ConcurrentHashMap<>();

  private final PasswordEncryptor passwordEncryptor;

  private final UserStruct userStruct;

  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(final UserStruct userStruct,
                         final PasswordEncryptor passwordEncryptor,
                         final UserMapper userMapper) {
    this.userStruct = userStruct;
    this.passwordEncryptor = passwordEncryptor;
    this.userMapper = userMapper;
  }


  @Override
  public ExistingUser createUser(final NewUser user) {

    final var newPassword = passwordEncryptor.encode(user.getPassword());

    final NewUser userWithRole = ImmutableNewUser.copyOf(user).withUserRole(UserRole.CLIENT);

    // Преобразовать NewUser в ExistingUser
    final var model = userStruct.fromNewUser(userWithRole, newPassword, null);

    UserModel newUser = userMapper.createUser(model);

    // Вернуть ExistingUser клиенту
    return userStruct.toExistingUser(model, newUser.getId(), newUser.getCreatedAt());
  }

  @Override
  public ExistingUser getById(long id) {
    return userStruct.toExistingUser(userMapper.findById(id));
  }

  @Override
  public ExistingUser update(long id, UpdatableUser updatableUser) {
    ExistingUser user = USERS.get(id);

    if (user == null) {
      return null;
    }

    ExistingUser updatedUser = userStruct.merge(user, updatableUser);

    USERS.put(id, updatedUser);

    return updatedUser;
  }

  @Override
  public boolean deleteById(long id) {
    return USERS.remove(id) != null;
  }
}
