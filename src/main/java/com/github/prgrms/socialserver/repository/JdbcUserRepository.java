package com.github.prgrms.socialserver.repository;

import com.github.prgrms.socialserver.model.Email;
import com.github.prgrms.socialserver.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import static com.github.prgrms.socialserver.util.DateTimeUtils.dateTimeOf;
import static com.github.prgrms.socialserver.util.DateTimeUtils.timeStampOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate template;

    public JdbcUserRepository(JdbcTemplate template) {
        this.template = template;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserRepository.class);

    public List<User> findAllUser() {
        return template.query("SELECT * FROM users",
                (rs, rowNum) -> new User(
                        rs.getLong("seq"),
                        new Email(rs.getString("email")),
                        rs.getString("passwd")
                )
        );
    }

    @Override
    public Optional<User> findById(Long userId) {
        List<User> results = template.query("SELECT * FROM users WHERE seq=?", mapper, userId);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("JdbcUserRepository findById results================> {}", results);
        }

        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public User insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(seq,email,passwd,login_count,last_login_at,create_at) VALUES (null,?,?,?,?,?)", new String[]{"seq"});
            preparedStatement.setString(1, user.getEmail().getAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getLoginCount());
            preparedStatement.setTimestamp(4, timeStampOf(user.getLastLoginAt().orElse(null)));
            preparedStatement.setTimestamp(5, timeStampOf(user.getCreateAt()));
            return preparedStatement;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedSeq = key != null ? key.longValue() : -1;

        return new User.Builder(user)
                .seq(generatedSeq)
                .build();
    }

    static RowMapper<User> mapper = (rs, rowNum) -> new User.Builder()
            .seq(rs.getLong("seq"))
            .email(new Email(rs.getString("email")))
            .password(rs.getString("passwd"))
            .loginCount(rs.getInt("login_count"))
            .lastLoginAt(dateTimeOf(rs.getTimestamp("last_login_at")))
            .createAt(dateTimeOf(rs.getTimestamp("create_at")))
            .build();
}
