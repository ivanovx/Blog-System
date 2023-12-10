package org.blogy.service;

import java.time.LocalDateTime;

import org.blogy.entity.Role;
import org.blogy.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.blogy.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public long count() {
        return userRepository.count();
    }

    @Transactional(readOnly = true)
    public User getUser(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with %s not found".formatted(username)));
    }

    //@Transactional(readOnly = true)
    //public boolean haveAdminUser() {
       // return userRepository.findAllByRole(Role.ADMIN).stream().count() > 0;
    //}

    @Transactional(readOnly = true)
    public boolean haveAdminUser() {
        return userRepository.existsAllByRole(Role.ADMIN);
    }

    public User createUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());

        user.setPassword(password);

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());

        user.setPassword(password);
        user.setModified(LocalDateTime.now());

        return userRepository.save(user);
    }
}