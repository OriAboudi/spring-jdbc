package com.amigoscode.user;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataAccessService implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByEmail(String email) {
        var sql = """
                SELECT *
                FROM jdbc.public.users
                WHERE email=?
                """;

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            User u = new User();
            u.setFirstname(rs.getString("firstname"));
            u.setLastname(rs.getString("lastname"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setCrateAt(rs.getString("creatAt"));
            u.setRoll(rs.getString("role"));
            return u;
        }, email));
    }
}

