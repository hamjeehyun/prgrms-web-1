package com.github.prgrms.socialserver.service;

import com.github.prgrms.socialserver.model.Email;
import com.github.prgrms.socialserver.model.User;
import com.github.prgrms.socialserver.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public User join(Email email, String password) {
        checkArgument(isNotEmpty(password), "password must be provided");
        checkArgument(
                password.length() >= 4 && password.length() <= 15,
                "password length must be between 4 and 15 characters."
        );

        User user = new User(email,password);
        return insert(user);
    }

    private User insert(User user) {
        return userRepository.insert(user);
    }
}

