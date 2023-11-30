package org.blogy.service;

import org.blogy.entity.User;

public interface UserService {
    long count();

    User getUser(String username);

    boolean haveAdminUser();

    User createUser(User user);

    User updateUser(User user);
}