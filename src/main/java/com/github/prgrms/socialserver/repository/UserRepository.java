package com.github.prgrms.socialserver.repository;

import com.github.prgrms.socialserver.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAllUser();

    Optional<User> findById(Long userId);

    User insert(User user);
}
