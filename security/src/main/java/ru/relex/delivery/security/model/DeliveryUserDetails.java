package ru.relex.delivery.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.relex.delivery.db.model.UserModel;

import java.util.Collection;
import java.util.Set;

public class DeliveryUserDetails implements UserDetails {

    private final String password;
    private final String username;
    private final boolean locked;
    private final boolean active;
    private final Set<SimpleGrantedAuthority> authorities;

    public DeliveryUserDetails(UserModel userModel) {
        this.password = userModel.getPassword();
        this.username = userModel.getUsername();
        this.locked = userModel.isLocked();
        this.active = userModel.isActive();
        this.authorities = Set.of(new SimpleGrantedAuthority("ROLE_" + userModel.getUserRole().name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
