package org.ivanovx.bloggable.service;

import org.ivanovx.bloggable.entity.User;

public interface UserService {
    long count();

    User getUser(String username);

    boolean haveAdminUser();

    User createUser(User user);

    User updateUser(User user);
}