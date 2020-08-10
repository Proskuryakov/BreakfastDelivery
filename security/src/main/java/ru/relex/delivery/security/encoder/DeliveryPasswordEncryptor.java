package ru.relex.delivery.security.encoder;


import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.relex.delivery.services.security.PasswordEncryptor;

@Component
public class DeliveryPasswordEncryptor implements PasswordEncryptor {

    private final PasswordEncoder passwordEncoder;

    public DeliveryPasswordEncryptor(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
