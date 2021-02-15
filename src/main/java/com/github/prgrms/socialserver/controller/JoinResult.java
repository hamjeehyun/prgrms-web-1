package com.github.prgrms.socialserver.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static com.google.common.base.Preconditions.checkNotNull;

public class JoinResult {

    private final UserDto user;

    public JoinResult(UserDto user) {
        checkNotNull(user, "user must be provided");
        this.user = user;
    }

    public UserDto getUser() {
        return user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("user", user)
                .toString();
    }
}
