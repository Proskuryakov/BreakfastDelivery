package ru.relex.delivery.db.model;

import lombok.Getter;
import lombok.Setter;
import ru.relex.delivery.commons.model.UserRole;

@Getter
@Setter
public class UserProfile {

    private long id;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;

    private UserRole role;

}
