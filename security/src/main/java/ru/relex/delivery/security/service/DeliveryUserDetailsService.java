package ru.relex.delivery.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.relex.delivery.db.mapper.UserAuthMapper;
import ru.relex.delivery.security.model.DeliveryUserDetails;

@Service
public class DeliveryUserDetailsService implements UserDetailsService {

    private final UserAuthMapper userMapper;

    public DeliveryUserDetailsService(UserAuthMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.getUserByUsernameOrEmailOrPhone(username)
                .map(DeliveryUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User with name [" + username + "] not found!"));
    }
}
