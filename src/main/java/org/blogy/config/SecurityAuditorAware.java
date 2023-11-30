package org.blogy.config;

import java.util.Optional;

import org.blogy.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class SecurityAuditorAware implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        Optional<User> user = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(securityContext -> securityContext.getAuthentication())
                .filter(authentication -> authentication.isAuthenticated())
                .map(authentication -> (User) authentication.getPrincipal());

        return user;
    }
}