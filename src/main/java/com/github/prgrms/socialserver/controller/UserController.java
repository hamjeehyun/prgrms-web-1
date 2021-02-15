package com.github.prgrms.socialserver.controller;

import com.github.prgrms.socialserver.dto.ApiResult;
import com.github.prgrms.socialserver.exception.NotFoundUserException;
import com.github.prgrms.socialserver.model.Email;
import com.github.prgrms.socialserver.model.User;
import com.github.prgrms.socialserver.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.prgrms.socialserver.dto.ApiResult.OK;
import static java.util.stream.Collectors.toList;

@RestController
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ApiResult<List<UserDto>> getUsers() {
        return OK(
                userService.findAllUser().stream()
                        .map(UserDto::new)
                        .collect(toList())
        );
    }

    @GetMapping("/api/users/{userId}")
    public ApiResult<UserDto> getUser(@PathVariable("userId") Long userId) throws NotFoundUserException {
        return OK(
                userService.findById(userId)
                        .map(UserDto::new)
                        .orElseThrow(() -> new NotFoundUserException("존재하지 않는 회원입니다."))
        );
    }

    @PostMapping("/api/users/join")
    public ApiResult<JoinResult> getUser(@RequestBody JoinRequest joinRequest) {
        User user = userService.join(new Email(joinRequest.getPrincipal()), joinRequest.getCredentials());
        return OK(
                new JoinResult(new UserDto(user))
        );
    }

}

