package com.github.prgrms.socialserver.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class User {
    private Long seq; // 불변
    private Email email; // 입력
    private String password; // 입력
    private int loginCount; // 가변
    private LocalDateTime lastLoginAt; // 가변
    private LocalDateTime createAt; // 불변

    public User(Email email, String password) {
        this(null, email, password, 0, null, null);
    }

    public User(Long seq, Email email, String password) {
        checkNotNull(seq, "seq must be provided");
        checkNotNull(email, "email must be provided");
        checkNotNull(password, "password must be provided");

        this.seq = seq;
        this.email = email;
        this.password = password;
    }

    public User(Long seq, Email email, String password, int loginCount, LocalDateTime lastLoginAt, LocalDateTime createAt) {
        checkNotNull(email, "email must be provided.");
        checkNotNull(password, "password must be provided.");

        this.seq = seq;
        this.email = email;
        this.password = password;
        this.loginCount = loginCount;
        this.lastLoginAt = lastLoginAt;
        this.createAt = defaultIfNull(createAt, now());
    }
    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getSeq() {
        return seq;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public Optional<LocalDateTime> getLastLoginAt() {
        return ofNullable(lastLoginAt);
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("email", email)
                .append("password", password)
                .append("loginCount", loginCount)
                .append("lastLoginAt", lastLoginAt)
                .append("createAt", createAt)
                .toString();
    }


    static public class Builder {
        private Long seq;
        private Email email;
        private String password;
        private int loginCount;
        private LocalDateTime lastLoginAt;
        private LocalDateTime createAt;

        public Builder() {
        }

        public Builder(User user) {
            this.seq = user.seq;
            this.email = user.email;
            this.password = user.password;
            this.loginCount = user.loginCount;
            this.lastLoginAt = user.lastLoginAt;
            this.createAt = user.createAt;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder email(Email email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder loginCount(int loginCount) {
            this.loginCount = loginCount;
            return this;
        }

        public Builder lastLoginAt(LocalDateTime lastLoginAt) {
            this.lastLoginAt = lastLoginAt;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public User build() {
            return new User(seq, email, password, loginCount, lastLoginAt, createAt);
        }
    }
}
